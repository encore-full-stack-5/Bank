package user.application;

import user.domain.Income;
import user.domain.User;
import user.repository.UserRepository;
import user.repository.UserRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository repository = UserRepositoryImpl.getInstance();

    static class Holder {
        static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImpl.Holder.INSTANCE;
    }

    @Override
    public User getUser(int id) throws Exception {
        return repository.findUserById(id);
    }

    @Override
    public List<User> getUserAll() throws Exception {
        return repository.findAllUser();
    }

    @Override
    public User signIn(String id, String passwd) throws Exception {
        User user = repository.findUserByLoginId(id);
        if (!user.getPassword().equals(passwd)) {
            throw new Exception("비밀번호가 맞지 않습니다.");
        }

        if (user.getDeleteTime() != null) {
            throw new Exception("탈퇴한 유저입니다.");
        }

        return user;
    }

    @Override
    public User signUp(SignUpRequestDto requestDto) throws Exception {
        // ID는 영숫자이고 밑줄을 포함할 수 있지만 문자로 시작하고 길이가 5~20자 사이여야 합니다.
        if (!requestDto.id().matches("^[A-Za-z][A-Za-z0-9_]{4,19}$")) {
            throw new Exception("아이디 양식이 유효하지 않습니다.");
        }
        // 비밀번호는 하나이상의 문자 + 6자리이상
        if (!requestDto.passwd().matches("^(?=.*[A-Za-z])[A-Za-z\\d]{6,}$")) {
            throw new Exception("비밀번호 양식이 유효하지 않습니다.");
        }
        if (!requestDto.email().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("이메일 양식이 유효하지 않습니다.");
        }
        if (!requestDto.phoneNumber().matches("^010\\d{8}$")) {
            throw new Exception("휴대폰 양식이 유효하지 않습니다.");
        }

        User validateUser = new User(
                requestDto.id(),
                requestDto.passwd(),
                requestDto.name(),
                requestDto.address(),
                requestDto.phoneNumber(),
                requestDto.email(),
                requestDto.job(),
                requestDto.income(),
                requestDto.asset(),
                requestDto.birth());
        validateUser.setCreatedTime(LocalDateTime.now());
        return repository.createUser(validateUser);
    }

    @Override
    public void unRegister(User user) throws Exception {
        repository.deleteUser(user);
    }

    @Override
    public void changePassword(User user, String password) throws Exception {
        if (!password.matches("^(?=.*[A-Za-z])[A-Za-z\\d]{6,}$")) {
            throw new Exception("비밀번호 양식이 유효하지 않습니다.");
        }
        repository.changePassword(user, password);
    }

    @Override
    public int measureCreditScore(User user) throws Exception {
        int totalScore = 0;
        int asset = user.getAsset(); // 예를 들어, 자산 단위를 만 단위로 가정
        Income income = user.getIncome(); // 월 수입을 가정

        if (asset >= 5000) {
            totalScore += 400;
        } else if (asset >= 1000) {
            totalScore += 250;
        } else {
            totalScore += 150;
        }

        switch (income) {
            case ZERO -> totalScore += 100;
            case OVER_200 -> totalScore += 200;
            case OVER_400 -> totalScore += 300;
            case OVER_600 -> totalScore += 400;
        }

        int loading = 0;
        while (loading < 100) {
            loading += 1 + (int)(Math.random() * 13);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(loading > 100) loading = 100;
            System.out.println("신용점수를 측정 중입니다 "+loading+"%");
        }

        return totalScore;
    }
}
