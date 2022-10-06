package ru.mikheev.impl;

import ru.mikheev.TestLog;
import ru.mikheev.annotation.Log;

public class TestLogImpl implements TestLog {

    @Log
    @Override
    public void calculation(int param1) {

    }

    @Override
    public void calculation(int param1, int param2) {

    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {

    }
}
