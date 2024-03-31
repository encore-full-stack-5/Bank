package reservation.application;

import bank.domain.Bank;
import common.util.ConsoleUtility;
import reservation.controller.ReservationController;
import reservation.controller.ReservationControllerImpl;
import reservation.domain.Reservation;
import reservation.repository.ReservationRepositoryDB;
import reservation.repository.ReservationRepositoryImplDB;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReservationServiceImpl implements ReservationService {

    ReservationRepositoryDB reservationRepository = ReservationRepositoryImplDB.getInstance();
    ReservationController reservationController = ReservationControllerImpl.getInstance();
    static class Holder {
        static final ReservationService INSTANCE = new ReservationServiceImpl();
    }

    public static ReservationService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception {
        reservationRepository.createReservation(UserId, choseReservationTime, choseBankId);
    }

    @Override
    public void printAvailableTime(int choseBankId,List<Bank> banks) throws Exception {
        List<Reservation> reservations = reservationRepository.findAvailableReservationsByBankId(choseBankId);
        System.out.println("* "+banks.get(choseBankId-1).getName()+"의 예약 가능한 시간");
        System.out.print("< ");
        String availableTimes = IntStream.rangeClosed(9, 15)
                .filter(time -> reservations.stream()
                        .noneMatch(reservation -> reservation.getReservationTime() == time))
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        System.out.print(availableTimes);

        System.out.println(" >");
        System.out.println();

    }

    @Override
    public boolean isAvailableTime(int choseBankId,int choseReservationTime) throws Exception {
        boolean availableTime =reservationRepository.isAvailableTime(choseBankId, choseReservationTime);
        return availableTime;
    }


}
