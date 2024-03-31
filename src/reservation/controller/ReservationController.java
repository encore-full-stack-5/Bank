package reservation.controller;

import bank.domain.Bank;
import reservation.domain.Reservation;

import java.util.List;

public interface ReservationController {
    void createReservation(int UserId, int choseReservationTime, int choseBankId ) throws Exception;

    void printAvailableTime(int choseBankId,List<Bank> banks) throws Exception;

    boolean isAvailableTime(int choseBankId,int choseReservationTime) throws Exception;
}