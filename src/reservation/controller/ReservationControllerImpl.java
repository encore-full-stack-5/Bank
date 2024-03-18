package reservation.controller;

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
    public List<Reservation> findAllReservationsById(int bankId) throws Exception{
        List<Reservation> reservationList =reservationService.findAllReservationsById(bankId);
        return reservationList;
    }

    @Override
    public void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception {
        reservationService.createReservation(UserId,choseReservationTime,choseBankId);
    }
}
