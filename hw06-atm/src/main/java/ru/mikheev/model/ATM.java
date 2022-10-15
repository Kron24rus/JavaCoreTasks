package ru.mikheev.model;

public interface ATM {
    void displayAvailableCache();

    void addMoney(BanknotePack banknotePack);

    void addMoney(Banknote banknote);

    BanknotePack getCache(int amountToWithdraw);
}
