package zhumaniyazov.boot.DAO;

import zhumaniyazov.boot.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    List<User> findAll();
    void delete(int id);
}