package reservation.repository;

import reservation.domain.Reservation;

import java.util.List;

public interface ReservationRepositoryDB {
    List<Reservation> findAllReservationsById(int bankId) throws Exception;

    void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception;

    boolean isAvailableTime(int choseBankId, int choseReservationTime) throws Exception;
}
