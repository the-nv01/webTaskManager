package webtaskmanager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webtaskmanager.model.Task;

import java.util.List;

public interface TaskService {

    void createTask (Task task);

    Task getTaskByCode(int id);

    int countTasks ();

    void editTask (int id, Task task);

    void deleteTask (int id);


}
