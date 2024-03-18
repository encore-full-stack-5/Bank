package bank.domain;

import java.time.LocalDateTime;

public class Bank {
    int id;
    String name;
    String address;
    String phoneNumber;

    LocalDateTime openTime;
    LocalDateTime closeTime;
    LocalDateTime createdTime;

    public Bank(int id, String name, String address, String phoneNumber, LocalDateTime openTime, LocalDateTime closeTime, LocalDateTime createdTime) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", createdTime=" + createdTime +
                '}';
    }

    public int id() {
        return this.id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }




}
