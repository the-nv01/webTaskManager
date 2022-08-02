package webtaskmanager.service;

import webtaskmanager.model.Task;

import java.util.List;

public interface TaskService {

    void createTask (Task task);

    Task getTaskByCode(int id);

    List<Task> getAllTasks ();

    void editTask (int id, Task task);

    void deleteTask (int id);


}
