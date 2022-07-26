package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import webtaskmanager.controller.TaskController;
import webtaskmanager.repository.TaskRepository;
import webtaskmanager.service.TaskServiceimpl;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTaskTest {

    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:8081/tasks").toString(), String.class);
        assertEquals("listTask", response.getBody());
    }


    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @Mock
    TaskServiceimpl taskService;

    @BeforeEach
    void setTaskService() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceimpl(taskRepository);
    }

    @Test
    void testControllerTask() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getParameter("a")).thenReturn("a");
        Model model = Mockito.mock(Model.class);

        assertEquals(taskController.search(model, Optional.of(1), request), "listTask");

    }

}
