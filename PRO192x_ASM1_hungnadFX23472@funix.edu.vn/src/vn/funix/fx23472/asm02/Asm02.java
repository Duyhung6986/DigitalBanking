package vn.funix.fx23472.asm02;


import vn.funix.fx23472.asm02.models.Account;
import vn.funix.fx23472.asm02.models.Bank;
import vn.funix.fx23472.asm02.models.Customer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asm02 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| NGÂN HÀNG SỐ  |  FX23472@v2.0.0                |");
        System.out.println("+----------+--------------------------+----------+");

        while (true) {
            System.out.println(" 1. Thêm khách hàng                             ");
            System.out.println(" 2. Thêm tài khoản cho khách hàng               ");
            System.out.println(" 3. Hiển thị danh sách khách hàng               ");
            System.out.println(" 4. Tìm theo CCCD                               ");
            System.out.println(" 5. Tìm theo tên khách hàng                     ");
            System.out.println(" 0. Thoát                                       ");
            System.out.println("+----------+--------------------------+----------+");
            System.out.print("Chọn chức năng: ");

//            Kiểm tra yêu cầu người dùng nhập số nguyên
            while (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại (0-5): ");
                scanner.next();

            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> addCustomer(bank, scanner);
                case 2 -> addAccountForCustomer(bank, scanner);
                case 3 -> displayCustomerList(bank);
                case 4 -> searchCustomerByCCCD(bank, scanner);
                case 5 -> searchCustomerByName(bank, scanner);
                case 0 -> {
                    System.out.println("Đã yêu cầu thoát.");
                    System.out.println("Xin cảm ơn và hẹn gặp lại!");
                    return;
                }
                default -> {
                    System.out.println("----------------");
                    System.out.println("Lựa chọn chức năng không đúng. Xin vui lòng chọn lại (0-5)!");
                    System.out.println("----------------");

                }
            }
        }
    }
    public static boolean isValidCCCD(String cccd) {
        // Kiểm tra CCCD có 12 chữ số
        String regex = "^[0-9]\\d{11}$";

        return !cccd.matches(regex);

    }



    //  THÊM KHÁCH HÀNG
    private static void addCustomer(Bank bank, Scanner scanner) {
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();

        String cccd;
        boolean isValid = false;
        do {
            System.out.print("Nhập số CCCD (12 chữ số): ");
            cccd = scanner.nextLine();

            if (isValidCCCD(cccd)) {
                System.out.println("Số CCCD không hợp lệ. Vui lòng nhập lại.");
            } else if (bank.isCustomerExisted(cccd)) {
                System.out.println("Số CCCD đã tồn tại.");
            } else {
                isValid = true;
            }
        } while (!isValid);


        Customer newCustomer = new Customer(name, cccd);
        bank.addCustomer(newCustomer);

        System.out.println("Thêm khách hàng thành công.");
    }


    //   Định dạng số tiền
    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###đ");
        return formatter.format(price);
    }
    //    THÊM TÀI KHOẢN KHÁCH HÀNG
    private static void addAccountForCustomer(Bank bank, Scanner scanner) {
//        Kiểm tra CCCD đã có hay chưa
        Customer customer = null;
        while (customer == null) {
            System.out.print("Vui lòng nhập số CCCD: ");
            String cccd = scanner.nextLine();
            customer = findCustomerByCCCD(bank, cccd);
            if (customer == null) {
                System.out.println("Không tìm thấy CCCD. Vui lòng nhập lại số CCCD!");
            }
        }


        System.out.print("Vui lòng nhập số tài khoản (có 6 chữ số): ");
        String accountNumber = scanner.nextLine();

        while (!accountNumber.matches("\\d{6}")) {
            System.out.print("Số tài khoản không đúng định dạng. Vui lòng nhập số tài khoản (có 6 chữ số): ");
            accountNumber = scanner.nextLine();
        }
        boolean isExistAccount = customer.isExistAccount(accountNumber);
        if (isExistAccount) {
            System.out.println("Tài khoản đã tồn tại.");
        } else {
            System.out.print("Nhập số dư: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            while (balance < 50000) {
                System.out.print("Yêu cầu nhập số dư tối thiểu là 50,000 VND. Vui lòng nhập lại số tiền: ");
                balance = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
            }

            Account newAccount = new Account(accountNumber, balance);
            customer.addAccount(newAccount);

        }
    }


    //    HIỂN THỊ DANH SÁCH KHÁCH HÀNG
    private static void displayCustomerList(Bank bank) {
        List<Customer> customers = bank.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("Không tìm thấy thông tin.");
            return;
        }

        System.out.println("\n===== DANH SÁCH KHÁCH HÀNG =====");
        for (Customer customer : customers) {
            customer.displayInformation();
            System.out.println();
        }
    }

    //    TÌM THEO CCCD
    private static void searchCustomerByCCCD(Bank bank, Scanner scanner) {
        System.out.print("Vui lòng nhập số CCCD cần tìm: ");
        String cccd = scanner.nextLine();

        Customer customer = findCustomerByCCCD(bank, cccd);

        if (customer == null) {
            System.out.println("Không tìm thấy thông tin.");
        } else {
            System.out.println("\n===== THÔNG TIN KHÁCH HÀNG CẦN TÌM =====");

            customer.displayInformation();
        }
    }

//    Duyệt qua CCCD trong danh sách khách hàng
    private static Customer findCustomerByCCCD(Bank bank, String cccd) {
        for (Customer customer : bank.getCustomers()) {
            if (customer.getCustomerId().equals(cccd)) {
                return customer;
            }
        }
        return null;
    }

    //    TÌM THEO TÊN
    private static void searchCustomerByName(Bank bank, Scanner scanner) {
        System.out.print("Vui lòng nhập tên cần tìm: ");
        String name = scanner.nextLine();

        List<Customer> matchingCustomers = findCustomersByName(bank, name);

        if (matchingCustomers.isEmpty()) {
            System.out.println("Không tìm thấy tên khách hàng cần tìm.");
        } else {
            System.out.println("\n===== THÔNG TIN KHÁCH HÀNG CẦN TÌM =====");
            for (Customer customer : matchingCustomers) {
                customer.displayInformation();
                System.out.println();
            }
        }
    }

    //    Duyệt qua tên trong danh sách khách hàng

    private static List<Customer> findCustomersByName(Bank bank, String name) {
        List<Customer> matchingCustomers = new ArrayList<>();
        for (Customer customer : bank.getCustomers()) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }
}