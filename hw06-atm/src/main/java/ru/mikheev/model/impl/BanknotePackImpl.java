package ru.mikheev.model.impl;

import ru.mikheev.model.Banknote;
import ru.mikheev.model.BanknotePack;
import ru.mikheev.model.Denomination;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BanknotePackImpl implements BanknotePack {

    private final Map<Denomination, Integer> cachePack = new HashMap<>();

    @Override
    public void addBanknotes(Banknote banknote, int amount) {
        Denomination denomination = banknote.getDenomination();
        if (cachePack.containsKey(denomination)) {
            cachePack.put(denomination, cachePack.get(denomination) + amount);
        } else {
            cachePack.put(denomination, amount);
        }
    }

    @Override
    public Map<Denomination, Integer> getCachePack() {
        return Collections.unmodifiableMap(cachePack);
    }
}
