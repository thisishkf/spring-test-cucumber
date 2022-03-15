package com.thisishkf.springcucumber.controller;

import com.thisishkf.springcucumber.model.request.SetLuckNumberRequest;
import com.thisishkf.springcucumber.service.LuckyNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/luckyNumber")
public class LuckyNumberController {
    @Autowired
    private LuckyNumberService luckyNumberService;

    @GetMapping(value = {"/", ""})
    public int getLuckyNumber() {
        return luckyNumberService.get();
    }

    @PostMapping(value = "/random")
    public int randomLuckyNumber() {
        return luckyNumberService.random();
    }

    @PostMapping(value = {"/", ""})
    public boolean setLuckyNumber(@Valid @RequestBody SetLuckNumberRequest request) {
        luckyNumberService.set(request.getNumber());
        return true;
    }

    @GetMapping(value = "/test")
    public boolean testLuckyNumber(){
        return luckyNumberService.isLucky();
    }
}
