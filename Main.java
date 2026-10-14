import java.time.Year;

// Cấp 1: Lớp cha Person
class Person {
    protected String name;
    protected int birthYear;
    protected String address;
    protected String phone;

    public Person(String name, int birthYear, String address, String phone) {
        this.name = name;
        this.birthYear = birthYear;
        this.address = address;
        this.phone = phone;
    }

    public int getAge() {
        return Year.now().getValue() - birthYear;
    }

    public void showInfo() {
        System.out.printf("Họ tên: %s | Tuổi: %d (Sinh năm: %d) | Địa chỉ: %s | SĐT: %s%n",
                name, getAge(), birthYear, address, phone);
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

// Cấp 2: Lớp con Employee kế thừa Person
class Employee extends Person {
    protected String id;
    protected String department;
    protected double baseSalary;
    protected int startYear;

    public Employee(String name, int birthYear, String address, String phone,
                    String id, String department, double baseSalary, int startYear) {
        super(name, birthYear, address, phone);
        this.id = id;
        this.department = department;
        this.baseSalary = baseSalary;
        this.startYear = startYear;
    }

    public int getYearsOfService() {
        return Math.max(0, Year.now().getValue() - startYear);
    }

    public double calculateSalary() {
        return baseSalary + (200000.0 * getYearsOfService());
    }

    public String getRole() {
        return "Employee (Nhân viên)";
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.printf("  -> Mã NV: %s | Phòng ban: %s | Vai trò: %s | Năm vào làm: %d (%d năm) | Lương cơ bản: %,.0f VNĐ | Tổng lương: %,.0f VNĐ%n",
                id, department, getRole(), startYear, getYearsOfService(), baseSalary, calculateSalary());
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public int getStartYear() { return startYear; }
    public void setStartYear(int startYear) { this.startYear = startYear; }
}

// Cấp 3: Lớp cháu Developer kế thừa Employee
class Developer extends Employee {
    private String language;
    private double overtimeHours;
    private double overtimeRate;

    public Developer(String name, int birthYear, String address, String phone,
                     String id, String department, double baseSalary, int startYear,
                     String language, double overtimeHours, double overtimeRate) {
        super(name, birthYear, address, phone, id, department, baseSalary, startYear);
        this.language = language;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + (overtimeHours * overtimeRate);
    }

    @Override
    public String getRole() {
        return "Developer (Lập trình viên)";
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.printf("  -> Chi tiết Dev: Ngôn ngữ: %s | Giờ OT: %.1f | Đơn giá OT: %,.0f VNĐ/h%n",
                language, overtimeHours, overtimeRate);
    }

    // Getters and Setters
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public double getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(double overtimeHours) { this.overtimeHours = overtimeHours; }
    public double getOvertimeRate() { return overtimeRate; }
    public void setOvertimeRate(double overtimeRate) { this.overtimeRate = overtimeRate; }
}

// Cấp 3: Lớp cháu Manager kế thừa Employee
class Manager extends Employee {
    private int teamSize;
    private double bonusPerMember;

    public Manager(String name, int birthYear, String address, String phone,
                   String id, String department, double baseSalary, int startYear,
                   int teamSize, double bonusPerMember) {
        super(name, birthYear, address, phone, id, department, baseSalary, startYear);
        this.teamSize = teamSize;
        this.bonusPerMember = bonusPerMember;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + (teamSize * bonusPerMember);
    }

    @Override
    public String getRole() {
        return "Manager (Quản lý)";
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.printf("  -> Chi tiết Manager: Số lượng thành viên: %d | Thưởng mỗi người: %,.0f VNĐ%n",
                teamSize, bonusPerMember);
    }

    // Getters and Setters
    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }
    public double getBonusPerMember() { return bonusPerMember; }
    public void setBonusPerMember(double bonusPerMember) { this.bonusPerMember = bonusPerMember; }
}

// Lớp chứa hàm main (Public class duy nhất trùng tên với file Main.java)
public class Main {
    public static void main(String[] args) {
        // 1. Tạo mảng Person[] gồm cả Employee, Developer, Manager
        Person[] staffList = new Person[] {
            new Employee("Nguyễn Văn An", 1995, "Hà Nội", "0901234567",
                    "EMP01", "Hành chính", 8000000, 2021),
            new Developer("Trần Thị Bình", 1998, "Đà Nẵng", "0912345678",
                    "DEV01", "IT", 15000000, 2022, "Java", 20, 150000),
            new Developer("Lê Hoàng Cường", 2000, "TP.HCM", "0923456789",
                    "DEV02", "IT", 14000000, 2023, "Python", 15, 120000),
            new Manager("Phạm Minh Đức", 1988, "Hà Nội", "0934567890",
                    "MGR01", "Dự án", 25000000, 2018, 10, 500000),
            new Manager("Hoàng Thu Hà", 1990, "Hải Phòng", "0945678901",
                    "MGR02", "Kinh doanh", 22000000, 2020, 8, 400000)
        };

        // 2. Duyệt mảng, gọi showInfo() cho từng người
        System.out.println("================ DANH SÁCH NHÂN SỰ ================");
        for (Person p : staffList) {
            p.showInfo();
            System.out.println("---------------------------------------------------");
        }

        // 3. Tính tổng lương của tất cả nhân viên & 4. Đếm số lượng Developer và Manager bằng instanceof
        double totalSalary = 0;
        int devCount = 0;
        int managerCount = 0;

        for (Person p : staffList) {
            if (p instanceof Employee) {
                Employee emp = (Employee) p;
                totalSalary += emp.calculateSalary();
            }
            if (p instanceof Developer) {
                devCount++;
            } else if (p instanceof Manager) {
                managerCount++;
            }
        }

        System.out.println("================ THỐNG KÊ ================");
        System.out.printf("Tổng lương của tất cả nhân viên: %,.0f VNĐ%n", totalSalary);
        System.out.println("Số lượng Developer: " + devCount);
        System.out.println("Số lượng Manager: " + managerCount);
    }
}
