package reservation.domain;

import java.time.LocalDateTime;

public class Reservation {
    int id;
    int reservationTime;
    int bankId;
    int userId;

    public Reservation(int id, int reservationTime, int bankId, int userId) {
        this.id = id;
        this.reservationTime = reservationTime;
        this.bankId = bankId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getReservationTime() {
        return reservationTime;
    }

    public int getBankId() {
        return bankId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationTime=" + reservationTime +
                ", bankId=" + bankId +
                ", userId=" + userId +
                '}';
    }
}
