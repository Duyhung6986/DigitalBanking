package vn.funix.fx23472.asm02.models;

public class User {
    private final String name;
    private String customerId;

    //    Khai báo tên khách hàng
    public User(String name, String customerId) {
        this.name = name;
        setCustomerId(customerId);
    }

    public void setCustomerId(String customerId) {
        if (isValidCCCD(customerId)) {
            this.customerId = customerId;
        }

    }


//    Kiểm tra format CCCD hợp lệ
    private boolean isValidCCCD(String cccd) {

        return cccd.matches("\\d{12}");
    }

    public String getCustomerId() {

        return customerId;
    }

    public String getName() {

        return name;
    }

}
