//package tests;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import webtaskmanager.model.Task;
//import webtaskmanager.repository.TaskRepository;
//import webtaskmanager.service.TaskServiceimpl;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TaskTest {
//
//    @Mock
//    TaskRepository taskRepository;
//
//    @InjectMocks
//    private Task task;
//
//    @InjectMocks
//    private TaskServiceimpl taskService;
//
//    @BeforeEach
//    void setTaskService() {
//        MockitoAnnotations.openMocks(this);
//        taskService = new TaskServiceimpl(taskRepository);
//        Mockito.when(taskRepository.save(task)).thenReturn(new Task());
//    }
//
//    @Test
//    void testSetTaskService() {
//        assertEquals(new Task(), taskService.createTask(task));
//    }
//
//}
