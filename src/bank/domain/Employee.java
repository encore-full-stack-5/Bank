package bank.domain;

import java.time.LocalDateTime;

public class Employee {
    int id;
    String name;
    String position;
    String phoneNumber;
    String email;
    int bankId;
    LocalDateTime createdTime;

    public Employee(int id, String name, String position, String phoneNumber, String email, int bankId, LocalDateTime createdTime) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bankId = bankId;
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getBankId() {
        return bankId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "직원 정보 {" +
                "\n   이름 : " + name +
                "\n   직책 : " + position +   // 보안상 비밀번호는 실제로 표시하지 않는 것이 좋습니다.
                "\n   전화번호 : " + phoneNumber +
                "\n   이메일 : " + email +
                '\n' + '}';
    }

}