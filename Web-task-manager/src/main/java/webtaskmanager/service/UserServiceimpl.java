package webtaskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtaskmanager.model.User;
import webtaskmanager.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.insert(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean checkUser(User user) {
        List<User> us = userRepository.findAll();
        for (int i = 0; i < us.size(); i++) {
            if (user.getUsername().equals(us.get(i).getUsername()) && user.getPassword().equals(us.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }
}
