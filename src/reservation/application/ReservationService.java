package reservation.application;

import reservation.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllReservationsById(int bankId) throws Exception;

    void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception;

}
