package user.repository;

import user.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> findAllUser() throws Exception;
    User findUserById(int uid) throws Exception;
    User findUserByLoginId(String id) throws Exception;
    User createUser(User user) throws Exception;
    void deleteUser(User user) throws Exception;
    void changePassword(User user, String newPasswrod) throws Exception;

}
