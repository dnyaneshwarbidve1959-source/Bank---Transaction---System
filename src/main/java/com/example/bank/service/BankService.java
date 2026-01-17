package com.example.bank.service;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;

@Service
public class BankService {

    private final AccountRepository repo;
    private final ReentrantLock lock = new ReentrantLock();

    public BankService(AccountRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {
        lock.lock();
        try {
            Account from = repo.findById(fromId).orElseThrow();
            Account to = repo.findById(toId).orElseThrow();

            if (from.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);

            repo.save(from);
            repo.save(to);
        } finally {
            lock.unlock();
        }
    }
}
