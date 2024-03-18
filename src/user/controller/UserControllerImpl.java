package user.controller;

import common.util.ConsoleUtility;
import user.application.SignUpRequestDto;
import user.application.UserService;
import user.application.UserServiceImpl;
import user.domain.Income;
import user.domain.Jobs;
import user.domain.User;
import java.util.List;

public class UserControllerImpl implements UserController {

    private final UserService userService = UserServiceImpl.getInstance();

    static class Holder {
        static final UserController INSTANCE = new UserControllerImpl();
    }
    public static UserController getInstance() {
        return UserControllerImpl.Holder.INSTANCE;
    }

    @Override
    public User signIn() throws Exception {
        String id = ConsoleUtility.promptForInput("아이디를 입력해주세요");
        String passwd = ConsoleUtility.promptForInput("비밀번호를 입력해주세요");
        return userService.signIn(id, passwd);
    }

    @Override
    public User signUp() throws Exception {

        String id = ConsoleUtility.promptForInput("아이디를 입력해주세요");
        String passwd = ConsoleUtility.promptForInput("비밀번호를 입력해주세요 문자하나포함 6자리이상");
        String name = ConsoleUtility.promptForInput("이름을 입력해주세요");
        String address = ConsoleUtility.promptForInput("주소를 입력해주세요");
        String phone = ConsoleUtility.promptForInput("핸드폰번호를 입력해주세요 - 없이작성");
        String email = ConsoleUtility.promptForInput("이메일을 입력해주세요 @들어가야함");
        int asset = ConsoleUtility.promptForInt("자산을 입력해주세요 단위:만원");
        String birth = ConsoleUtility.promptForInput("생년월일을 입력해주세요 -> xxxx-xx-xx");

        int jobChoice = ConsoleUtility.promptForChoice("직업을 선택해주세요 : 1.무직 2.학생 3.아르바이트 4.직장인", 1,4);
        Jobs jobs = Jobs.fromCommand(jobChoice);
        int incomeChoice = ConsoleUtility.promptForChoice("급여를 선택해주세요 : 1.없음 2.200만원 이상 3.400만원 이상 4.600만원 이상", 1,4);
        Income income = Income.fromCommand(incomeChoice);

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(
                id,
                passwd,
                name,
                address,
                phone,
                email,
                jobs,
                income,
                asset,
                birth
        );
        return userService.signUp(signUpRequestDto);
    }

    @Override
    public User getUser(int id) throws Exception {
        return userService.getUser(id);
    }

    @Override
    public List<User> getUserList() throws Exception {
        return userService.getUserAll();
    }

    @Override
    public void unResgiter(User user) throws Exception {
        userService.unRegister(user);
    }

    @Override
    public void changePassword(User user) throws Exception {
        String newPassword = ConsoleUtility.promptForInput("새로운 비밀번호를 설정해주세요");
        userService.changePassword(user, newPassword);
    }

    @Override
    public int measureCreditScore(User user) throws Exception {
        return userService.measureCreditScore(user);
    }
}
