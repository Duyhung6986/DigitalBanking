package vn.funix.fx23472.asm02.models;

import vn.funix.fx23472.asm02.Asm02;

public class Account {
    private String accountNumber;
    private double balance;

//    Ghi nhận số dư vào tài khoản
    public Account(String accountNumber, double balance) {
        setAccountNumber(accountNumber);
        setBalance(balance);
    }

    public String getAccountNumber() {

        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public double getBalance() {

        return balance;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }

    public boolean isPremium() {

        return balance >= 10000000;
    }
// Hiển thị danh sách tài khoản của khách hàng
    @Override
    public String toString() {
        return String.format("%11s | %53s",accountNumber, Asm02.priceWithDecimal(balance));
    }
}
