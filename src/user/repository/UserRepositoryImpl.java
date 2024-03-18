package user.repository;

import db.DataBase;
import db.DB;
import db.DataBaseImpl;
import user.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserRepositoryImpl implements UserRepository {

    static class Holder {
        static final UserRepository INSTANCE = new UserRepositoryImpl();
    }
    public static UserRepository getInstance() {
        return Holder.INSTANCE;
    }

    DataBase db = DataBaseImpl.getInstance();
    TreeMap<Integer,User> table = db.getUserTable();

    @Override
    public List<User> findAllUser() throws Exception {
        return table.values().stream().toList();
    }
    @Override
    public User findUserById(int uid) throws Exception {
        return table.get(uid);
    }

    @Override
    public User findUserByLoginId(String id) throws Exception {
        for (Map.Entry<Integer, User> entry : table.entrySet()) {
            User user = entry.getValue();
            if(user.getLoginId().equals(id)) return user;
        }
        throw new Exception("찾는 아이디가 없습니다.");
    }

    @Override
    public User createUser(User user) throws Exception {
        int autoId = table.size();
        LocalDateTime now = LocalDateTime.now();
        user.setUid(autoId);
        user.setCreatedTime(now);
        user.setDeleteTime(null);
        table.put(autoId,user);
        return user;
    }

    @Override
    public void deleteUser(User user) throws Exception {
        User target = table.get(user.getUid());
        if (target != null) {
            target.setDeleteTime(LocalDateTime.now());
            table.remove(user.getUid());
            table.put(user.getUid(), target);
        } else {
            throw new Exception("해당 유저가 없습니다.");
        }
    }

    @Override
    public void changePassword(User user, String newPasswrod) throws Exception {
        User target = table.get(user.getUid());
        if (target != null) {
            target.setPassword(newPasswrod);
            table.remove(user.getUid());
            table.put(user.getUid(), target);
        } else {
            throw new Exception("비밀번호 변경에 실패하였습니다.");
        }
    }
}
