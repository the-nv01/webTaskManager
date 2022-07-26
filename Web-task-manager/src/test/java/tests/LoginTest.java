package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import webtaskmanager.model.User;
import webtaskmanager.repository.UserRepository;
import webtaskmanager.service.UserServiceimpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceimpl userServiceimpl;

    @InjectMocks
    private List<User> list;

    @BeforeEach
    void setUserService() {
        list.add(new User("the", "the"));
        MockitoAnnotations.openMocks(this);
        userServiceimpl = new UserServiceimpl(userRepository);
        Mockito.when(userRepository.findAll()).thenReturn(list);
    }

    @Test
    void testUserService() {
        assertTrue(userServiceimpl.checkUser(new User("the", "the")));
    }

}
