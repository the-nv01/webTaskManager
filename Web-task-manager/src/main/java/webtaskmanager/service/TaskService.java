package webtaskmanager.service;

import webtaskmanager.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask (Task task);

    Task getTaskByCode (String id);

    List<Task> getAllTask ();

    Task editTask (String id, Task task);

    void deleteTask (Task task);


}
