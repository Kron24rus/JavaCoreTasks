package ru.mikheev;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        TestLog log = LogProxyLoader.getTestLog();
        log.calculation(1);
        log.calculation(1,2);
        log.calculation(1,2,"3");
    }
}
