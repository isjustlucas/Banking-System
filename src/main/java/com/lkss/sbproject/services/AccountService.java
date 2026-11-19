package com.lkss.sbproject.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lkss.sbproject.entities.Account;
import com.lkss.sbproject.exceptions.AccountNotFoundException;
import com.lkss.sbproject.repositories.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(String holder) {
        if (holder == null || holder.isBlank()) {
            throw new IllegalArgumentException("Holder's name is obligatory");
        }
        return repository.save(new Account(holder));
    }

    public Account findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Transactional
    public Account deposit(Long id, BigDecimal amount) {
        Account account = findById(id);
        account.deposit(amount);
        return repository.save(account);
    }

    @Transactional
    public Account withdraw(Long id, BigDecimal amount) {
        Account account = findById(id);
        account.withdraw(amount);
        return repository.save(account);
    }
}
