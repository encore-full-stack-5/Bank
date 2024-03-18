package bank.repository;

import account.domain.Account;
import account.domain.AccountType;
import bank.domain.Bank;
import bank.domain.Employee;
import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImplDB implements BankRepositoryDB {

    static class Holder{
        static final BankRepositoryDB INSTANCE = new BankRepositoryImplDB();
    }

    public static BankRepositoryDB getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Employee findEmployeeById(int employeeId) throws Exception {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractEmployee(rs);
                }
            }
        }
        throw new Exception("해당 직원 정보를 찾을 수 없습니다.");
    }

    @Override
    public List<Bank> findAllBanks() throws Exception {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT * FROM Bank";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                banks.add(extractBank(rs));
            }
        }
        return banks;
    }

    @Override
    public List<Employee> findEmployeesByBankId(int bankId) throws Exception {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee where bankId = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bankId);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employees.add(extractEmployee(rs));
                }
            }
        }
        return employees;
    }

    @Override
    public List<Account> findAccountsByEmployeeId(int employeeId) throws Exception {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account where employeeId = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    accounts.add(extractAccount(rs));
                }
            }
        }
        return accounts;
    }

    private Employee extractEmployee(ResultSet rs) throws Exception {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("position"),
                rs.getString("phoneNumber"),
                rs.getString("email"),
                rs.getInt("bankId"),
                rs.getTimestamp("createdTime").toLocalDateTime()
        );
    }

    private Bank extractBank(ResultSet rs) throws Exception {
        return new Bank(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phoneNumber"),
                rs.getTimestamp("openTime").toLocalDateTime(),
                rs.getTimestamp("closeTime").toLocalDateTime(),
                rs.getTimestamp("createdTime").toLocalDateTime()
        );
    }

    private Account extractAccount(ResultSet rs) throws Exception {
        String isType = rs.getString("type");
        return new Account(
                rs.getString("accountNumber"),
                0, AccountType.valueOf(isType),
                rs.getInt("bankId"),
                rs.getInt("userId"),
                rs.getInt("employeeId"),
                null,
                rs.getFloat("interestRate")
        );
    }
}
