package com.example.bank.controller;

import org.springframework.web.bind.annotation.*;

import com.example.bank.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from,
                            @RequestParam Long to,
                            @RequestParam double amount) {
        service.transfer(from, to, amount);
        return "Transfer Successful";
    }
}
