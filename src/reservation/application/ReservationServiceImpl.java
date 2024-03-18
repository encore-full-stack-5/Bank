package reservation.application;

import reservation.domain.Reservation;
import reservation.repository.ReservationRepositoryDB;
import reservation.repository.ReservationRepositoryImplDB;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    ReservationRepositoryDB reservationRepository = ReservationRepositoryImplDB.getInstance();

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
}
