package ru.mikheev.model;

import ru.mikheev.model.Denomination;

public class Banknote {

    private final Denomination denomination;

    public Banknote(Denomination denomination) {
        this.denomination = denomination;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int getBanknoteValue() {
        return denomination.value;
    }
}
