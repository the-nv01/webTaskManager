package webtaskmanager.service;

import org.springframework.data.domain.Pageable;
import webtaskmanager.model.Task;
import webtaskmanager.model.User;

import java.util.List;

public interface TaskService {

    void insertTask(Task task);

    Task findById(int id);

    List<Task> findAllByPage(String title, String action, Pageable pageable);

    int countTasks();

    void updateTask(Task task);

    void deleteTask(int id);
}
