package ru.mikheev.model.impl;

import ru.mikheev.exception.ATMException;
import ru.mikheev.model.ATM;
import ru.mikheev.model.Banknote;
import ru.mikheev.model.BanknotePack;
import ru.mikheev.model.Denomination;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATMImpl implements ATM {

    private final Map<Denomination, Integer> cache = new TreeMap<>(Collections.reverseOrder());

    @Override
    public void displayAvailableCache() {
        cache.forEach((banknote, amount) -> {
            System.out.println("Banknote: " + banknote + " amount: " + amount);
        });
    }

    @Override
    public void addMoney(BanknotePack banknotePack) {
        banknotePack.getCachePack().forEach((denomination, amount) -> {
            if (cache.containsKey(denomination)) {
                cache.put(denomination, cache.get(denomination) + amount);
            } else {
                cache.put(denomination, amount);
            }
        });
    }

    @Override
    public void addMoney(Banknote banknote) {
        if (cache.containsKey(banknote.getDenomination())) {
            cache.put(banknote.getDenomination(), cache.get(banknote.getDenomination()) + 1);
        } else {
            cache.put(banknote.getDenomination(), 1);
        }
    }

    @Override
    public BanknotePack getCache(int amountToWithdraw) {
        BanknotePack banknotePack = prepareBanknotePack(amountToWithdraw);
        withdrawMoney(banknotePack);
        return banknotePack;
    }

    private BanknotePack prepareBanknotePack(int amountToWithdraw) {
        int amountLeft = amountToWithdraw;

        BanknotePack banknotePack = new BanknotePackImpl();

        for (Map.Entry<Denomination, Integer> entry : cache.entrySet()) {
            Denomination denomination = entry.getKey();
            int amountInATM = entry.getValue();
            if (amountLeft >= denomination.value) {
                int banknotesNumber = amountLeft / denomination.value;
                banknotePack.addBanknotes(new Banknote(denomination), Math.min(banknotesNumber, amountInATM));
                amountLeft -= Math.min(banknotesNumber, amountInATM) * denomination.value;
            }
        }

        if (amountLeft != 0) {
            throw new ATMException("Requested value can't be withdrawn");
        }

        return banknotePack;
    }

    private void withdrawMoney(BanknotePack banknotePack) {
        Map<Denomination, Integer> banknotesToWithdraw= banknotePack.getCachePack();
        cache.forEach((denomination, amount) -> {
            if (banknotesToWithdraw.containsKey(denomination)) {
                cache.put(denomination, amount - banknotesToWithdraw.get(denomination));
            }
        });
    }
}
