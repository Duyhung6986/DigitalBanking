package vn.funix.fx23472.asm02.models;

import vn.funix.fx23472.asm02.Asm02;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Account> accounts;

    public Customer(String name, String customerId) {
        super(name, customerId);
        accounts = new ArrayList<>();
    }

    //    Kiểm tra khách hàng là Primium hay Normal
    public boolean isPremium() {
        for (Account account : accounts) {
            if (account.isPremium()) {
                return true;
            }
        }
        return false;
    }

//    Kiểm tra sự tồn tại của tài khoản khi tạo tài khoản mới
    public void addAccount(Account newAccount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return;
            }
        }
        accounts.add(newAccount);
    }



//    Kiểm tra sự tồn tại của số tài khoản
public boolean isExistAccount (String accountNumber) {
    for (Account account : accounts) {
        if (account.getAccountNumber().equals(accountNumber)) {
            return true;
        }
    }
    return false;
}
//  Tổng số tiền từ của các tài khoản vào TotalBlance của khách hàng
    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

//    Hiển thị thông tin số tài khoản khách hàng
    public void displayInformation() {
        System.out.format("%s  | %20s | %7s | %20s", getCustomerId(), getName(), (isPremium() ? "Premium" : "Normal"), Asm02.priceWithDecimal(getTotalBalance()));
//
        System.out.println();
        int accountNo = 1;
        for (Account account : accounts) {
            System.out.println(accountNo + " " + account);
            accountNo++;
        }
    }

}
