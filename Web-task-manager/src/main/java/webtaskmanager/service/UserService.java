package webtaskmanager.service;

import webtaskmanager.model.User;

import java.util.List;

public interface UserService {

    User createUser (User user);

    List<User> getAllUser();

    boolean checkUser (User user);
}
