package me.hengwei.t.javaee.spring.transactional;

import lombok.Getter;
import lombok.Setter;

/**
 * Bank Account entity object.
 */
public class Account {
    @Setter
    @Getter
    private String number;
    @Setter
    @Getter
    private Double balance;

    public Account() {

    }

    public Account(String number, Double initialBalance) {
        super();
        this.number = number;
        this.balance = initialBalance;
    }

    public void debit(Double debitAmount){
        this.balance = this.balance - debitAmount;
    }

    public void credit(Double creditAmount) {
        this.balance = this.balance + creditAmount;
    }

    @Override
    public String toString() {
        return "Account [number=" + number + ", balance=" + balance + "]";
    }
}
