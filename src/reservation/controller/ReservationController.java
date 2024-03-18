package reservation.controller;

import bank.domain.Bank;
import reservation.domain.Reservation;

import java.util.List;

public interface ReservationController {
    List<Reservation> findAllReservationsById(int bankId) throws Exception;
    void createReservation(int UserId, int choseReservationTime, int choseBankId ) throws Exception;

    void printAvailableTime(List<Reservation> reservations,int choseBankId,List<Bank> banks) throws Exception;

    boolean isAvailableTime(int choseBankId,int choseReservationTime) throws Exception;
}