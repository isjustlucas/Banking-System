package com.lkss.sbproject.entities;

import java.math.BigDecimal;

import com.lkss.sbproject.exceptions.InsufficientBalanceException;
import com.lkss.sbproject.exceptions.InvalidAmountException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String holder;
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(String holder) {
        this.holder = holder;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException();
        }
        balance = balance.subtract(amount);
    }
}
