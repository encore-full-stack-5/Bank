package user.controller;

import user.domain.User;

import java.util.List;

public interface UserController {

    User signIn() throws Exception;
    User signUp() throws Exception;
    User getUser(int id) throws Exception;
    List<User> getUserList() throws Exception;
    void unResgiter(User user) throws Exception;
    void changePassword(User user) throws Exception;
    int measureCreditScore(User user) throws Exception;

}
