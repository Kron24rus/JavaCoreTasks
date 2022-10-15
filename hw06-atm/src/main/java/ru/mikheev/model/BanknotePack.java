package ru.mikheev.model;

import java.util.Map;

public interface BanknotePack {
    void addBanknotes(Banknote banknote10, int amount);

    Map<Denomination, Integer> getCachePack();
}
