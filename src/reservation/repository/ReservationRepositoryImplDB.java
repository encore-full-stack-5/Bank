package reservation.repository;

import bank.domain.Bank;
import db.DB;
import reservation.domain.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImplDB implements ReservationRepositoryDB {

    static class Holder{
        static final ReservationRepositoryDB INSTANCE = new ReservationRepositoryImplDB();
    }

    public static ReservationRepositoryDB getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Reservation> findAllReservationsById(int bankId) throws Exception {
        List<Reservation> banks = new ArrayList<>();
        String sql = "SELECT * FROM Reservation WHERE bankId ="+bankId;
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                banks.add(extractReservation(rs));
            }
        }
        return banks;
    }

    @Override
    public void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception {
        String sql = "INSERT INTO Reservation (reservationTime, bankId, userId) VALUES (?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, choseReservationTime);
            stmt.setInt(2, choseBankId);
            stmt.setInt(3, UserId);
            int affectedRows = stmt.executeUpdate();
        }
    }

    @Override
    public boolean isAvailableTime(int choseBankId,int choseReservationTime) throws Exception {
        List<Reservation> reserved = new ArrayList<>();
        String sql = "SELECT * FROM Reservation WHERE bankId ="+choseBankId+" and reservationTime = "+choseReservationTime;
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reserved.add(extractReservation(rs));
            }
        }
        if(reserved.size()==0){
            return true;
        }
        return false;
    }

    private Reservation extractReservation(ResultSet rs) throws Exception {
        return new Reservation(
                rs.getInt("id"),
                rs.getInt("reservationTime"),
                rs.getInt("bankId"),
                rs.getInt("userId")
        );
    }
}
