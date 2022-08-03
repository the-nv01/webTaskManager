package webtaskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtaskmanager.mapper.UserMapper;
import webtaskmanager.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
