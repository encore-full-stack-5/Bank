package db;

import account.domain.Account;
import account.domain.AccountType;
import account.domain.TransactionHistory;
import bank.domain.Bank;
import bank.domain.Employee;
import user.domain.Income;
import user.domain.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.TreeMap;

import static user.domain.Jobs.FULL_TIME;

public class DataBaseImpl implements DataBase {

    private static class DatabaseHolder {
        static final DataBaseImpl INSTANCE = new DataBaseImpl();
    }

    public static DataBase getInstance() {
        return DatabaseHolder.INSTANCE;
    }

    private TreeMap<Integer, User> userTable;
    private TreeMap<Integer, Account> accountTable;
    private TreeMap<Integer, Bank> bankTable;
    private TreeMap<Integer, Employee> employeeTable;
    private TreeMap<Integer, TransactionHistory> transactionTable;

    private DataBaseImpl() {
        initDataBase();
    }

    private void initDataBase() {
        initUserDB();
        initAccountDB();
        initBankDB();
        initTransactionTable();
        initEmployeeTable();
    }

    private void initUserDB() {
        TreeMap<Integer,User> userTable = new TreeMap<>();
        User dummy = new User(
                "test",
                "12345a",
                "테스트",
                "성남",
                "01012345678",
                "test@gmail.com",
                FULL_TIME,
                Income.OVER_400,
                1000,
                "1996"
        );
        dummy.setUid(1);
        dummy.setCreatedTime(LocalDateTime.now());
        userTable.put(1,dummy);
        this.userTable = userTable;
    }

    private void initAccountDB() {
//        TreeMap<Integer,Account> accountTable = new TreeMap<>();
//        Account dummy = new Account(
//                AccountType.DEPOSIT,
//                1,
//                1,
//                1,
//                "1111",
//                0.5f
//        );
//        dummy.setAccountNumber("111-11");
//        dummy.setId(1);
//        dummy.setCreatedTime(LocalDateTime.now());
//        accountTable.put(1,dummy);
//        this.accountTable = accountTable;

//        System.out.println("@#@#@#@#@#@#@#");
//        System.out.println(accountTable.toString());
    }

    private void initBankDB() {
        TreeMap<Integer, Bank> banks = new TreeMap<>();
        LocalDateTime currentTime = LocalDateTime.now();

        Arrays.stream(new Bank[] {
                new Bank(1, "부산진구지점", "부산시 부산진구", "051-000-0000", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(2, "중구지점", "대구시 중구", "053-001-0001", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(3, "유성구지점", "대전시 유성구", "042-002-0002", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(4, "남구지점", "광주시 남구", "062-003-0003", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(5, "연수구지점", "인천시 연수구", "032-004-0004", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(6, "수성구지점", "대구시 수성구", "053-005-0005", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(7, "성산구지점", "창원시 성산구", "055-006-0006", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(8, "덕진구지점", "전주시 덕진구", "063-007-0007", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(9, "해운대구지점", "부산시 해운대구", "051-008-0008", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime),
                new Bank(10, "서귀포지점", "제주특별자치도 서귀포시", "064-009-0009", LocalDateTime.of(2024, 3, 14, 9, 0), LocalDateTime.of(2024, 3, 14, 16, 0), currentTime)
        }).forEach(bank -> banks.put(bank.id(), bank));
//        System.out.println("bank +++++++++++++++++");
//        banks.forEach((integer, bank) -> System.out.println(bank));

        this.bankTable = banks;
    }

    private void initEmployeeTable() {
        TreeMap<Integer, Employee> employees = new TreeMap<>();
        LocalDateTime currentTime = LocalDateTime.now();
        Arrays.stream(new Employee[]{
                new Employee(1, "김은행", "지점장", "010-0000-0001", "manager1@bank1.com", 1, currentTime),
                new Employee(2, "이은행", "차장", "010-0000-0002", "deputy1@bank1.com", 1, currentTime),
                new Employee(3, "박은행", "과장", "010-0000-0003", "manager2@bank1.com", 1, currentTime),
                new Employee(4, "최은행", "대리", "010-0000-0004", "assistant1@bank1.com", 1, currentTime),
                new Employee(5, "정은행", "사원", "010-0000-0005", "employee1@bank1.com", 1, currentTime),
                new Employee(6, "김신한", "지점장", "010-0001-0001", "manager1@bank2.com", 2, currentTime),
                new Employee(7, "이신한", "차장", "010-0001-0002", "deputy1@bank2.com", 2, currentTime),
                new Employee(8, "박신한", "과장", "010-0001-0003", "manager2@bank2.com", 2, currentTime),
                new Employee(9, "최신한", "대리", "010-0001-0004", "assistant1@bank2.com", 2, currentTime),
                new Employee(10, "정신한", "사원", "010-0001-0005", "employee1@bank2.com", 2, currentTime),
                new Employee(11, "김우리", "지점장", "010-0002-0001", "manager1@bank3.com", 3, currentTime),
                new Employee(12, "이우리", "차장", "010-0002-0002", "deputy1@bank3.com", 3, currentTime),
                new Employee(13, "박우리", "과장", "010-0002-0003", "manager2@bank3.com", 3, currentTime),
                new Employee(14, "최우리", "대리", "010-0002-0004", "assistant1@bank3.com", 3, currentTime),
                new Employee(15, "정우리", "사원", "010-0002-0005", "employee1@bank3.com", 3, currentTime),
                new Employee(16, "김하나", "지점장", "010-0003-0001", "manager1@bank4.com", 4, currentTime),
                new Employee(17, "이하나", "차장", "010-0003-0002", "deputy1@bank4.com", 4, currentTime),
                new Employee(18, "박하나", "과장", "010-0003-0003", "manager2@bank4.com", 4, currentTime),
                new Employee(19, "최하나", "대리", "010-0003-0004", "assistant1@bank4.com", 4, currentTime),
                new Employee(20, "정하나", "사원", "010-0003-0005", "employee1@bank4.com", 4, currentTime),
                new Employee(21, "김농협", "지점장", "010-0004-0001", "manager1@bank5.com", 5, currentTime),
                new Employee(22, "이농협", "차장", "010-0004-0002", "deputy1@bank5.com", 5, currentTime),
                new Employee(23, "박농협", "과장", "010-0004-0003", "manager2@bank5.com", 5, currentTime),
                new Employee(24, "최농협", "대리", "010-0004-0004", "assistant1@bank5.com", 5, currentTime),
                new Employee(25, "정농협", "사원", "010-0004-0005", "employee1@bank5.com", 5, currentTime),
                new Employee(26, "김기업", "지점장", "010-0005-0001", "manager1@bank6.com", 6, currentTime),
                new Employee(27, "이기업", "차장", "010-0005-0002", "deputy1@bank6.com", 6, currentTime),
                new Employee(28, "박기업", "과장", "010-0005-0003", "manager2@bank6.com", 6, currentTime),
                new Employee(29, "최기업", "대리", "010-0005-0004", "assistant1@bank6.com", 6, currentTime),
                new Employee(30, "정기업", "사원", "010-0005-0005", "employee1@bank6.com", 6, currentTime),
                new Employee(31, "김수협", "지점장", "010-0006-0001", "manager1@bank7.com", 7, currentTime),
                new Employee(32, "이수협", "차장", "010-0006-0002", "deputy1@bank7.com", 7, currentTime),
                new Employee(33, "박수협", "과장", "010-0006-0003", "manager2@bank7.com", 7, currentTime),
                new Employee(34, "최수협", "대리", "010-0006-0004", "assistant1@bank7.com", 7, currentTime),
                new Employee(35, "정수협", "사원", "010-0006-0005", "employee1@bank7.com", 7, currentTime),
                new Employee(36, "김제일", "지점장", "010-0007-0001", "manager1@bank8.com", 8, currentTime),
                new Employee(37, "이제일", "차장", "010-0007-0002", "deputy1@bank8.com", 8, currentTime),
                new Employee(38, "박제일", "과장", "010-0007-0003", "manager2@bank8.com", 8, currentTime),
                new Employee(39, "최제일", "대리", "010-0007-0004", "assistant1@bank8.com", 8, currentTime),
                new Employee(40, "정제일", "사원", "010-0007-0005", "employee1@bank8.com", 8, currentTime),
                new Employee(41, "김씨티", "지점장", "010-0008-0001", "manager1@bank9.com", 9, currentTime),
                new Employee(42, "이씨티", "차장", "010-0008-0002", "deputy1@bank9.com", 9, currentTime),
                new Employee(43, "박씨티", "과장", "010-0008-0003", "manager2@bank9.com", 9, currentTime),
                new Employee(44, "최씨티", "대리", "010-0008-0004", "assistant1@bank9.com", 9, currentTime),
                new Employee(45, "정씨티", "사원", "010-0008-0005", "employee1@bank9.com", 9, currentTime),
                new Employee(46, "김카카오", "지점장", "010-0009-0001", "manager1@bank10.com", 10, currentTime),
                new Employee(47, "이카카오", "차장", "010-0009-0002", "deputy1@bank10.com", 10, currentTime),
                new Employee(48, "박카카오", "과장", "010-0009-0003", "manager2@bank10.com", 10, currentTime),
                new Employee(49, "최카카오", "대리", "010-0009-0004", "assistant1@bank10.com", 10, currentTime),
                new Employee(50, "정카카오", "사원", "010-0009-0005", "employee1@bank10.com", 10, currentTime)
        }).forEach(employee -> employees.put(employee.getId(),employee));

//        System.out.println("employees +++++++++++++++++");
//        employees.forEach((integer, employee) -> System.out.println(employee));
        this.employeeTable = employees;
    }

    private void initTransactionTable() {
        transactionTable = new TreeMap<>();
    }

    @Override
    public TreeMap<Integer, User> getUserTable() {
        return userTable;
    }

    @Override
    public TreeMap<Integer, Account> getAccountTable() {
        return accountTable;
    }

    @Override
    public TreeMap<Integer, Bank> getBankTable() {
        return bankTable;
    }

    @Override
    public TreeMap<Integer, Employee> getEmployeeTable() {
        return employeeTable;
    }

    @Override
    public TreeMap<Integer, TransactionHistory> getTransactionTable() {
        return transactionTable;
    }

}