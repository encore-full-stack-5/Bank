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
}