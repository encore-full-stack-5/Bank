package user.repository;

import db.DB;
import user.domain.Income;
import user.domain.Jobs;
import user.domain.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryRealDB implements UserRepository {

    static class Holder {
        static final UserRepository INSTANCE = new UserRepositoryRealDB();
    }

    public static UserRepository getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<User> findAllUser() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(extractUser(rs));
            }
        }
        return users;
    }

    @Override
    public User findUserById(int uid) throws Exception {
        String sql = "SELECT * FROM User WHERE uid = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, uid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractUser(rs);
                }
            }
        }
        throw new Exception("찾는 아이디가 없습니다.");
    }

    @Override
    public User findUserByLoginId(String loginId) throws Exception {
        String sql = "SELECT * FROM User WHERE loginId = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loginId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractUser(rs);
                }
            }
        }
        throw new Exception("찾는 아이디가 존재하지 않습니다");
    }

    @Override
    public User createUser(User user) throws Exception {
        String sql = "INSERT INTO User (loginId, password, name, address, phoneNumber, email, job, income, asset, birth, createdTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getLoginId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getJob().name());
            stmt.setString(8, user.getIncome().name());
            stmt.setInt(9, user.getAsset());
            stmt.setDate(10, java.sql.Date.valueOf(user.getBirth()));
            stmt.setTimestamp(11, java.sql.Timestamp.valueOf(user.getCreatedTime()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("유저 생성에 실패하였습니다.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUid(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("유저 생성에 실패하였습니다.");
                }
            }
        }
        return user;
    }

    @Override
    public void deleteUser(User user) throws Exception {
        String sql = "DELETE FROM User WHERE uid = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUid());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("계정 탈퇴중 에러가 발생했습니다..");
            }
        }
    }

    @Override
    public void changePassword(User user, String newPassword) throws Exception {
        String sql = "UPDATE User SET password = ? WHERE uid = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, user.getUid());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("계정생성에 실패하였습니다.");
            }
        }
    }

    private User extractUser(ResultSet rs) throws Exception {
        try {
            User user = new User(
                    rs.getString("loginId"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    Jobs.valueOf(rs.getString("job")),
                    Income.valueOf(rs.getString("income")),
                    rs.getInt("asset"),
                    rs.getString("birth")
            );

            user.setUid(rs.getInt("uid"));
            Timestamp createdTime = rs.getTimestamp("createdTime");
            LocalDateTime localDateTime = null;
            if (createdTime != null) {
                localDateTime = createdTime.toLocalDateTime();
                user.setCreatedTime(localDateTime);
            }

            Timestamp deleteTime = rs.getTimestamp("deleteTime");
            LocalDateTime createdTimeToLocal = null;
            if (deleteTime != null) {
                createdTimeToLocal = deleteTime.toLocalDateTime();
                user.setDeleteTime(createdTimeToLocal);
            }

            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}