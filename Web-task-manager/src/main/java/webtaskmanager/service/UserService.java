package webtaskmanager.service;

import webtaskmanager.model.User;

import java.util.List;

public interface UserService {

    void insertUser(User user);

    List<User> findAllUsers();

    User selectUserByUsername(String username);
}
