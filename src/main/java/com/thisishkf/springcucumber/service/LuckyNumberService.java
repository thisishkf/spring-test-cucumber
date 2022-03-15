package com.thisishkf.springcucumber.service;

public interface LuckyNumberService {
    int random();

    void set(int number);

    int get();

    boolean isLucky();
}
