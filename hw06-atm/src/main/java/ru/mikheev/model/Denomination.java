package ru.mikheev.model;

public enum Denomination {

    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    FIVE_THOUSAND(5000);

    public final int value;

    Denomination(int value) {
        this.value = value;
    }

}
