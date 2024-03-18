package user.application;

import user.domain.User;

import java.util.List;

public interface UserService {
    User getUser(int id) throws Exception;
    List<User> getUserAll() throws Exception;
    User signIn(String id, String passwd) throws Exception;
    User signUp(SignUpRequestDto requestDto) throws Exception;
    void unRegister(User user) throws Exception;
    void changePassword(User user, String password) throws Exception;
    int measureCreditScore(User user) throws Exception;

}
