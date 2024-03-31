package reservation.controller;

import bank.domain.Bank;
import reservation.application.ReservationService;
import reservation.application.ReservationServiceImpl;
import reservation.domain.Reservation;

import java.util.List;

public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService = ReservationServiceImpl.getInstance();
    private static ReservationControllerImpl reservationController;

    public static ReservationController getInstance() {
        if(reservationController == null) reservationController = new ReservationControllerImpl();
        return reservationController;
    }

    @Override
    public void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception {
        reservationService.createReservation(UserId,choseReservationTime,choseBankId);
    }

    @Override
    public void printAvailableTime(int choseBankId,List<Bank> banks) throws Exception {
        reservationService.printAvailableTime(choseBankId,banks);
    }

    @Override
    public boolean isAvailableTime(int choseBankId,int choseReservationTime) throws Exception {
        boolean availableTime =reservationService.isAvailableTime(choseBankId, choseReservationTime);
        return availableTime;
    }


}
