package reservation.application;

import bank.domain.Bank;
import common.util.ConsoleUtility;
import reservation.controller.ReservationController;
import reservation.controller.ReservationControllerImpl;
import reservation.domain.Reservation;
import reservation.repository.ReservationRepositoryDB;
import reservation.repository.ReservationRepositoryImplDB;

import java.util.List;

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
    public List<Reservation> findAllReservationsById(int bankId) throws Exception{
        return reservationRepository.findAllReservationsById(bankId);
    }

    @Override
    public void createReservation(int UserId, int choseReservationTime, int choseBankId) throws Exception {
        reservationRepository.createReservation(UserId, choseReservationTime, choseBankId);
    }

    @Override
    public void printAvailableTime(List<Reservation> reservations,int choseBankId,List<Bank> banks) throws Exception {
        boolean isReservated = false;
        System.out.println("* "+banks.get(choseBankId-1).getName()+"의 예약 가능한 시간");
        System.out.print("< ");

        for (int i = 0; i<=7;i++){
            isReservated = false;
            for (int j = 0; j < reservations.size(); j++) {
                if((i+8) == reservations.get(j).getReservationTime()){
                    isReservated = true;
                }
            }
            if(isReservated == false){
                System.out.print((i+8)+"시 ");
            }
        } // 해당 지점에 예약이 되어있는 시간을 제외하고 모두 출력해줌
        System.out.println(">");
        System.out.println();

    }


}
