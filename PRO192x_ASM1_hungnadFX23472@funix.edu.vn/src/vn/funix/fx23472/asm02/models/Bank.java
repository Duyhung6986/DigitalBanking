package vn.funix.fx23472.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final List<Customer> customers;

//    Tạo ID ngẫu nhiên
private String id;
    public Bank() {
        this.customers = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }


//    Bổ sung đối tượng khách hàng vào danh sách khách hàng
    public void addCustomer(Customer newCustomer) {
        if (!isCustomerExisted(newCustomer.getCustomerId())) {
            customers.add(newCustomer);
        }
    }


//    Kiểm tra tồn tại của CCCD hay chưa
    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    //    Trả về danh sách khách hàng
    public List<Customer> getCustomers() {
        return customers;
    }
}
