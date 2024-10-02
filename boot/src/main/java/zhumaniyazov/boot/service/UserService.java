package zhumaniyazov.boot.service;


import zhumaniyazov.boot.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void update(User user);
    List<User> findAll();
    void delete(int id);
}