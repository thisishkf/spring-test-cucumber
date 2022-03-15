package com.thisishkf.springcucumber.service.impl;

import com.thisishkf.springcucumber.service.LuckyNumberService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class LuckyNumberServiceImpl implements LuckyNumberService {
    private Integer number;
    @Override
    public synchronized int random() {
        SecureRandom secureRandom = new SecureRandom();
        this.number =  Math.abs(secureRandom.nextInt());
        return this.number;
    }

    @Override
    public synchronized void set(int number) {
        this.number = number;
    }

    @Override
    public int get() {
        return this.number;
    }

    @Override
    public boolean isLucky() {
        return this.number % 2 == 0;
    }
}
