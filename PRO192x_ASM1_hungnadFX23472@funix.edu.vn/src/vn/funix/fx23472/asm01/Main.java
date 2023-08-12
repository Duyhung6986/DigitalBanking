package vn.funix.fx23472.asm01;

import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| NGÂN HÀNG SỐ  |  FX23472@1.0.0                 |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| 1. Nhập CCCD                                   |");
        System.out.println("| 0. Thoát                                       |");
        System.out.println("+----------+--------------------------+----------+");

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("1")) {
            System.out.print("Chọn chức năng: ");
            input = sc.next();
            if (!input.equals("0") && !input.equals("1")) {
                System.out.println("+------------+----------+");
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            } else if (input.equals("0")) {
                System.out.println("Chương trình kết thúc.");
                System.exit(0);
            } else {

//                LỰA CHỌN KIỂU XÁC THỰC 3 CHỮ SỐ HOẶC 6 KÝ TỰ
                String choise = "";
                boolean isValid = false;
                while (!choise.equals("1") && (!isValid)) {
                    System.out.println("Vui lòng chọn kiểu xác thực:");
                    System.out.println("  | 1. Chọn kiểu xác thực 3 chữ số.");
                    System.out.println("  | 2. Chọn kiểu xác thực 6 ký tự.");

                    choise = sc.next();
                    if (!choise.equals("1") && !choise.equals("2")) {
                        System.out.println("+------------+----------+");
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    } else if (choise.equals("1")) {

//                        TẠO MÃ NGẪU NHIÊN CÓ 3 CHỮ SỐ
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(900) + (100);
                        while (true) {
                            System.out.println("Mã xác thực: " + randomNumber);
                            System.out.print("Vui lòng nhập xác thực: ");
                            int userInput;
                            try {
                                userInput = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("+------------+----------+");
                                System.out.println("Mã xác thực không đúng. Vui lòng thử lại!");
                                sc.nextLine();
                                continue;
                            }
                            if (userInput != randomNumber) {
                                System.out.println("+------------+----------+");
                                System.out.println("Mã xác thực không đúng. Vui lòng thử lại!");
                            } else {
                                break;
                            }
                        }
                    } else {

//                        YÊU CẦU NHẬP MÃ XÁC THỰC CÓ 6 KÝ TỰ
                        String code = generateCode();
                        System.out.println("Mã xác thực của bạn là: " + code);
                        while (!isValid) {
                            String codeInput = getUserInput();
                            isValid = validateCode(codeInput.toUpperCase(), code.toUpperCase());

                            if (isValid) {
                                System.out.println("Mã xác thực chính xác.");
                            } else {
                                System.out.println("+------------+----------+");
                                System.out.println("Mã xác thực không chính xác. Vui lòng thử lại!");
                            }
                        }
                    }
                }
            }
        }

        //        YÊU CẦU NHẬP ĐÚNG SỐ CCCD ĐÚNG THEO FORMAT
        Scanner scanner = new Scanner(System.in);
        String cccd;
        System.out.print("Nhập CCCD có 12 chữ số: ");
        do {
            cccd = scanner.nextLine();
            if (cccd.equalsIgnoreCase("no")) {
                System.exit(0);
            } else if (cccd.length() == 12) {
                try {
                    Long.parseLong(cccd);
                } catch (Exception e) {
                    System.out.println("+------------+----------+");
                    System.out.println("CCCD không hợp lệ.");
                    System.out.print("Vui lòng nhập lại số CCCD hoặc 'no' để thoát: ");
                }
            } else {
                System.out.println("+------------+----------+");
                System.out.println("CCCD không hợp lệ.");
                System.out.print("Vui lòng nhập lại số CCCD hoặc 'no' để thoát: ");
            }
        }

//        LỰA CHỌN KIỂM TRA THÔNG TIN SỐ CCCD
        while (cccd.length() != 12);
        Scanner scan = new Scanner(System.in);
        String checkInput;
        while (true) {
            System.out.println("  | 1. Kiểm tra nơi sinh");
            System.out.println("  | 2. Kiểm tra năm sinh, giới tính");
            System.out.println("  | 3. Kiểm tra số ngẫu nhiên");
            System.out.println("  | 0. Thoát");
            System.out.print("Chọn chức năng: ");
            checkInput = scan.next();
            if (!checkInput.equals("0") && !checkInput.equals("1") && !checkInput.equals("2") && !checkInput.equals("3")) {
                System.out.println("+------------+----------+");
                System.out.println("Yêu cầu không hợp lệ. Vui lòng chọn lại!");
            } else if (checkInput.equals("0")) {
                System.out.println("Chương trình kết thúc.");
                System.exit(0);
            } else if (checkInput.equals("1")) {
                String provinceCode = cccd.substring(0, 3);
                Map<String, String> provinceMap = createProvinceMap();
                String province = provinceMap.getOrDefault(provinceCode, "Không xác định được tỉnh thành.");
                System.out.println(province);
            } else if (checkInput.equals("2")) {
                String gioiTinhCode = cccd.substring(3, 4);
                String namSinhCode = cccd.substring(4, 6);
                Map<String, String> gioiTinhMap = createGioiTinh(namSinhCode);
                String gioiTinh = gioiTinhMap.getOrDefault(gioiTinhCode, "Không xác định được giới tính.");
                System.out.println(gioiTinh);
            }
            else {
                String soNgauNhien = cccd.substring(6);
                System.out.println("Số ngẫu nhiên: " +soNgauNhien);
            }
        }
    }
//    TẠO MÃ NGẪU NHIÊN CÓ 6 KÝ TỰ
    public static String generateCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã xác thực: ");
        return scanner.nextLine().toUpperCase();
    }

    public static boolean validateCode(String userInput, String code) {
        return userInput.equals(code);
    }
//    MAP ĐỐI CHIẾU TỈNH THÀNH
    private static Map<String, String> createProvinceMap() {
        Map<String, String> provinceMap = new HashMap<>();
        provinceMap.put("001", "Nơi sinh: Hà Nội");
        provinceMap.put("002", "Nơi sinh: Hà Giang");
        provinceMap.put("004", "Nơi sinh: Cao Bằng");
        provinceMap.put("006", "Nơi sinh: Bắc Kạn");
        provinceMap.put("008", "Nơi sinh: Tuyên Quang");
        provinceMap.put("010", "Nơi sinh: Lào Cai");
        provinceMap.put("011", "Nơi sinh: Điện Biên");
        provinceMap.put("012", "Nơi sinh: Lai Châu");
        provinceMap.put("014", "Nơi sinh: Sơn La");
        provinceMap.put("015", "Nơi sinh: Yên Bái");
        provinceMap.put("017", "Nơi sinh: Hoà Bình");
        provinceMap.put("019", "Nơi sinh: Thái Nguyên");
        provinceMap.put("020", "Nơi sinh: Lạng Sơn");
        provinceMap.put("022", "Nơi sinh: Quảng Ninh");
        provinceMap.put("024", "Nơi sinh: Bắc Giang");
        provinceMap.put("025", "Nơi sinh: Phú Thọ");
        provinceMap.put("026", "Nơi sinh: Vĩnh Phúc");
        provinceMap.put("027", "Nơi sinh: Bắc Ninh");
        provinceMap.put("030", "Nơi sinh: Hải Dương");
        provinceMap.put("031", "Nơi sinh: Hải Phòng");
        provinceMap.put("033", "Nơi sinh: Hưng Yên");
        provinceMap.put("034", "Nơi sinh: Thái Bình");
        provinceMap.put("035", "Nơi sinh: Hà Nam");
        provinceMap.put("036", "Nơi sinh: Nam Định");
        provinceMap.put("037", "Nơi sinh: Ninh Bình");
        provinceMap.put("038", "Nơi sinh: Thanh Hoá");
        provinceMap.put("040", "Nơi sinh: Nghệ An");
        provinceMap.put("042", "Nơi sinh: Hà Tĩnh");
        provinceMap.put("044", "Nơi sinh: Quảng Bình");
        provinceMap.put("045", "Nơi sinh: Quảng Trị");
        provinceMap.put("046", "Nơi sinh: Thừa Thiên Huế");
        provinceMap.put("048", "Nơi sinh: Đà Nẵng");
        provinceMap.put("049", "Nơi sinh: Quảng Nam");
        provinceMap.put("051", "Nơi sinh: Quảng Ngãi");
        provinceMap.put("052", "Nơi sinh: Bình Định");
        provinceMap.put("054", "Nơi sinh: Phú Yên");
        provinceMap.put("056", "Nơi sinh: Khánh Hoà");
        provinceMap.put("058", "Nơi sinh: Ninh Thuận");
        provinceMap.put("060", "Nơi sinh: Bình Thuận");
        provinceMap.put("062", "Nơi sinh: Kon Tum");
        provinceMap.put("064", "Nơi sinh: Gia Lai");
        provinceMap.put("066", "Nơi sinh: Đắk Lắk");
        provinceMap.put("067", "Nơi sinh: Đắk Nông");
        provinceMap.put("068", "Nơi sinh: Lâm Đồng");
        provinceMap.put("070", "Nơi sinh: Bình Phước");
        provinceMap.put("072", "Nơi sinh: Tây Ninh");
        provinceMap.put("074", "Nơi sinh: Bình Dương");
        provinceMap.put("075", "Nơi sinh: Đồng Nai");
        provinceMap.put("077", "Nơi sinh: Bà Rịa - Vũng Tàu");
        provinceMap.put("079", "Nơi sinh: TP Hồ Chí Minh");
        provinceMap.put("080", "Nơi sinh: Long An");
        provinceMap.put("082", "Nơi sinh: Tiền Giang");
        provinceMap.put("083", "Nơi sinh: Bến Tre");
        provinceMap.put("084", "Nơi sinh: Trà Vinh");
        provinceMap.put("086", "Nơi sinh: Vĩnh Long");
        provinceMap.put("087", "Nơi sinh: Đồng Tháp");
        provinceMap.put("089", "Nơi sinh: An Giang");
        provinceMap.put("091", "Nơi sinh: Kiên Giang");
        provinceMap.put("092", "Nơi sinh: Cần Thơ");
        provinceMap.put("093", "Nơi sinh: Hậu Giang");
        provinceMap.put("094", "Nơi sinh: Sóc Trăng");
        provinceMap.put("095", "Nơi sinh: Bạc Liêu");
        provinceMap.put("096", "Nơi sinh: Cà Mau");
        return provinceMap;
    }

//    MAP ĐỐI CHIẾU GIỚI TÍNH VÀ NĂM SINH THEO THẾ KỶ
    private static Map<String, String> createGioiTinh(String namSinhCode) {
        Map<String, String> gioiTinhMap = new HashMap<>();

        gioiTinhMap.put("0", "Giới tính: Nam  | Năm sinh: 19" + namSinhCode);
        gioiTinhMap.put("1", "Giới tính: Nữ  | Năm sinh: 19" + namSinhCode);
        gioiTinhMap.put("2", "Giới tính: Nam  | Năm sinh: 20" + namSinhCode);
        gioiTinhMap.put("3", "Giới tính: Nữ  | Năm sinh: 20" + namSinhCode);
        gioiTinhMap.put("4", "Giới tính: Nam  | Năm sinh: 21" + namSinhCode);
        gioiTinhMap.put("5", "Giới tính: Nữ  | Năm sinh: 21" + namSinhCode);
        gioiTinhMap.put("6", "Giới tính: Nam  | Năm sinh: 22" + namSinhCode);
        gioiTinhMap.put("7", "Giới tính: Nữ  | Năm sinh: 22" + namSinhCode);
        gioiTinhMap.put("8", "Giới tính: Nam  | Năm sinh: 23" + namSinhCode);
        gioiTinhMap.put("9", "Giới tính: Nữ  | Năm sinh: 23" + namSinhCode);
        return gioiTinhMap;
    }
}


