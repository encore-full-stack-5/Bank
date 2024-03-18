package account.repository;

import account.domain.Account;
import account.domain.AccountType;
import account.domain.TransactionHistory;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    static class Holder {
        static final AccountRepository INSTANCE = new AccountRepositoryImpl();
    }

    public static AccountRepository getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Account createAccount(Account account) throws Exception {
        // 계좌 생성 SQL 쿼리
        String sql = "INSERT INTO Account (accountNumber, balance, type, bankId, userId, employeeId, password, interestRate, createdTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setInt(2, account.getBalance());
            stmt.setString(3, account.getType().name());
            stmt.setInt(4, account.getBankId());
            stmt.setInt(5, account.getUserId());
            stmt.setInt(6, account.getEmployeeId());
            stmt.setString(7, account.getPassword());
            stmt.setFloat(8, account.getInterestRate());
            stmt.setTimestamp(9, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                    return account;
                } else {
                    throw new Exception("계좌 생성 실패, ID를 가져올 수 없습니다.");
                }
            }
        }
    }

    @Override
    public List<Account> findAccountsByUserId(int userId) throws Exception {
        // 유저 ID로 계좌 찾기 SQL 쿼리
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE userId = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    accounts.add(extractAccount(rs));
                }
            }
        }
        return accounts;
    }

    @Override
    public Account findAccountById(int accountId) throws Exception {
        // 계좌 ID로 계좌 찾기 SQL 쿼리
        String sql = "SELECT * FROM Account WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractAccount(rs);
                }
            }
        }
        throw new Exception("해당 ID를 가진 계좌가 존재하지 않습니다: " + accountId);
    }

    @Override
    public void deleteAccountById(int id) throws Exception {
        // 계좌 ID로 계좌 삭제 SQL 쿼리
        String sql = "DELETE FROM Account WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("계좌 삭제 실패: 해당 ID를 가진 계좌가 없습니다. " + id);
            }
        }
    }

    @Override
    public TransactionHistory createDepositTransaction(Account account, int amount) throws Exception {
        String sql = "INSERT INTO TransactionHistory (type, totalAmount, accountId, amount, createdTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, "DEPOSIT");
            stmt.setInt(2, account.getBalance()); // 입금 후 총액
            stmt.setInt(3, account.getId()); // 계좌 ID
            stmt.setInt(4, amount); // 입금액
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now())); // 현재 시각
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    updateAccountBalance(conn, account.getId(), account.getBalance());
//                    conn.commit();

                    return new TransactionHistory(
                            generatedKeys.getInt(1), "DEPOSIT", account.getBalance(), account.getId(), amount, LocalDateTime.now()
                    );
                } else {
                    throw new Exception("입금 트랜잭션 생성 실패, ID를 가져올 수 없습니다.");
                }
            }
        }
    }

    @Override
    public TransactionHistory createWithdrawTransaction(Account account, int amount) throws Exception {
        // 출금 트랜잭션을 데이터베이스에 추가하는 SQL 쿼리
        String sql = "INSERT INTO TransactionHistory (type, totalAmount, accountId, amount, createdTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, "WITHDRAW");
            stmt.setInt(2, account.getBalance()); // 출금 후 총액
            stmt.setInt(3, account.getId()); // 계좌 ID
            stmt.setInt(4, amount); // 출금액
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now())); // 현재 시각
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    updateAccountBalance(conn, account.getId(), account.getBalance());
//                    conn.commit();

                    return new TransactionHistory(
                            generatedKeys.getInt(1), "WITHDRAW", account.getBalance(), account.getId(), amount, LocalDateTime.now()
                    );
                } else {
                    throw new Exception("출금 트랜잭션 생성 실패, ID를 가져올 수 없습니다.");
                }
            }
        }
    }

    private void updateAccountBalance(Connection conn, int accountId, int newBalance) throws Exception {
        // 계좌 잔액을 업데이트하는 SQL 쿼리
        String sql = "UPDATE Account SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newBalance);
            stmt.setInt(2, accountId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("계좌 업데이트 실패: 해당 ID를 가진 계좌가 없습니다. " + accountId);
            }
        }
    }
    @Override
    public List<TransactionHistory> findTransactionsById(int accountId) throws Exception {
        // 주어진 계좌 ID에 대한 모든 트랜잭션을 찾는 SQL 쿼리
        List<TransactionHistory> transactions = new ArrayList<>();
        String sql = "SELECT * FROM TransactionHistory WHERE accountId = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new TransactionHistory(
                            rs.getInt("id"), rs.getString("type"), rs.getInt("totalAmount"),
                            rs.getInt("accountId"), rs.getInt("amount"),
                            rs.getTimestamp("createdTime").toLocalDateTime() // null 처리 확인 필요
                    ));
                }
            }
        }
        return transactions;
    }

    private Account extractAccount(ResultSet rs) throws Exception {
        // ResultSet에서 받은 데이터로부터 Account 객체 생성
        Account account = new Account(
                rs.getString("accountNumber"),
                rs.getInt("balance"),
                AccountType.valueOf(rs.getString("type")), // 데이터베이스의 'type' 컬럼은 AccountType enum과 일치해야 합니다.
                rs.getInt("bankId"),
                rs.getInt("userId"),
                rs.getInt("employeeId"),
                rs.getString("password"),
                rs.getFloat("interestRate")
        );
        account.setId(rs.getInt("id")); // id는 생성자에 없기 때문에 별도로 설정
        Timestamp createdTime = rs.getTimestamp("createdTime");
        LocalDateTime localDateTime = null;
        if (createdTime != null) {
            localDateTime = createdTime.toLocalDateTime();
            account.setCreatedTime(localDateTime);
        }

        return account;
    }

}