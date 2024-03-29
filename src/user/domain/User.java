package user.domain;

import java.time.LocalDateTime;

public class User {

    Integer uid;
    String loginId;
    String password;
    String name;
    String address;
    String phoneNumber;
    String email;
    Jobs job;
    Income income;
    int asset;
    String birth;
    LocalDateTime createdTime;
    LocalDateTime deleteTime;

    public User(String id, String password, String name, String address, String phoneNumber, String email, Jobs job, Income income, int asset, String birth) {
        this.loginId = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.job = job;
        this.income = income;
        this.asset = asset;
        this.birth = birth;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Jobs getJob() {
        return job;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Integer getUid() {
        return uid;
    }

    public LocalDateTime getDeleteTime() { return deleteTime; }

    public Income getIncome() {
        return income;
    }

    public int getAsset() {
        return asset;
    }

    public String getBirth() {
        return birth;
    }

    public int getAge() throws Exception {
        return LocalDateTime.now().getYear() - Integer.parseInt(birth);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", id='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", job=" + job +
                ", income=" + income +
                ", asset=" + asset +
                ", birth='" + birth + '\'' +
                ", createdTime=" + createdTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}