package account.domain;

import java.time.LocalDateTime;

public class Account {

    private int id;
    private String accountNumber;
    private int balance;
    private AccountType type;
    private int bankId;
    private int userId;
    private int employeeId;
    private String password;
    private float interestRate;
    private LocalDateTime createdTime;
    private int lockCount = 0;

    public Account(String accountNumber, int balance, AccountType type, int bankId, int userId, int employeeId, String password, float interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.bankId = bankId;
        this.userId = userId;
        this.employeeId = employeeId;
        this.password = password;
        this.interestRate = interestRate;
    }

    public void deposit(int money) {
        this.balance += money;
    }

    public void withdraw(int money) throws Exception {
        if (this.balance < money) throw new Exception("잔액이 부족합니다");
        this.balance -= money;
    }

    public void validatePassword(int password) throws Exception {
        if (password == Integer.parseInt(this.password)) {
            lockCount = 0;
            return;
        }
        System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요");
        lockCount++;
        if(lockCount >= 3) throw new Exception("비밀번호가 3번 틀렸습니다. 처음부터 다시 시작해주세요");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public int getBankId() {
        return bankId;
    }

    public int getUserId() {
        return userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                ", bankId=" + bankId +
                ", userId=" + userId +
                ", employeeId=" + employeeId +
                ", password='" + password + '\'' +
                ", interestRate=" + interestRate +
                ", createdTime=" + createdTime +
                '}';
    }
}
