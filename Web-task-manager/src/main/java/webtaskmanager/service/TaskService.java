package webtaskmanager.service;

import webtaskmanager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask (Task task);

    Optional<Task> getTaskByCode (int id);

    List<Task> getAllTask ();

    Task editTask (int id, Task task);

    void deleteTask (Task task);


}
