package webtaskmanager.service;

import webtaskmanager.model.task;

import java.util.List;

public interface taskServiceimpl {

    task createTask (task task);

    task getTaskByCode (String id);

    List<task> getAllTask ();

    task editTask (String id, task task);

    void deleteTask (task task);


}
