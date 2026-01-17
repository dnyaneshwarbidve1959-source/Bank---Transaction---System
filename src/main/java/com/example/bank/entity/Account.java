package com.example.bank.entity;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;
    private double balance;

    @Version
    private int version;

    public Long getId() { return id; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }

    public void setOwner(String owner) { this.owner = owner; }
    public void setBalance(double balance) { this.balance = balance; }
}
