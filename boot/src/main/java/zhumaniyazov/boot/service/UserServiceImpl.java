package zhumaniyazov.boot.service;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import zhumaniyazov.boot.DAO.UserDao;
import zhumaniyazov.boot.model.User;

import java.util.List;

@Service
@Repository
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user){
         userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}