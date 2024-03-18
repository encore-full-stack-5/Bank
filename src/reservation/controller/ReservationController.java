package reservation.controller;

import reservation.domain.Reservation;

import java.util.List;

public interface ReservationController {
    List<Reservation> findAllReservationsById(int bankId) throws Exception;
    void createReservation(int UserId, int choseReservationTime, int choseBankId ) throws Exception;
}