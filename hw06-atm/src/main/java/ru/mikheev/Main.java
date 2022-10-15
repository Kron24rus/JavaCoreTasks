package ru.mikheev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mikheev.exception.ATMException;
import ru.mikheev.model.ATM;
import ru.mikheev.model.Banknote;
import ru.mikheev.model.BanknotePack;
import ru.mikheev.model.Denomination;
import ru.mikheev.model.impl.ATMImpl;
import ru.mikheev.model.impl.BanknotePackImpl;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Banknote banknote10 = new Banknote(Denomination.TEN);
        Banknote banknote50 = new Banknote(Denomination.FIFTY);
        Banknote banknote100 = new Banknote(Denomination.HUNDRED);
        Banknote banknote200 = new Banknote(Denomination.TWO_HUNDRED);
        Banknote banknote500 = new Banknote(Denomination.FIVE_HUNDRED);
        Banknote banknote1000 = new Banknote(Denomination.THOUSAND);
        Banknote banknote5000 = new Banknote(Denomination.FIVE_THOUSAND);

        BanknotePack banknotePack = new BanknotePackImpl();

        banknotePack.addBanknotes(banknote10, 5000);
        banknotePack.addBanknotes(banknote50, 1000);
        banknotePack.addBanknotes(banknote100, 500);
        banknotePack.addBanknotes(banknote200, 200);
        banknotePack.addBanknotes(banknote500, 100);
        banknotePack.addBanknotes(banknote1000, 50);
        banknotePack.addBanknotes(banknote5000, 10);

        ATM atm = new ATMImpl();
        atm.addMoney(banknotePack);
        atm.addMoney(banknote100);

        atm.displayAvailableCache();

        BanknotePack withdrawCache = atm.getCache(125110);

        try {
            withdrawCache = atm.getCache(12501);
        } catch (ATMException e) {
            logger.error("Error during withdrawing cache.", e);
        }

        atm.displayAvailableCache();
    }
}
