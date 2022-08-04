package webtaskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webtaskmanager.mapper.TaskMapper;
import webtaskmanager.model.Task;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void insertTask(Task task) {
        taskMapper.insertTask(task);
    }

    @Override
    public List<Task> findAllTasks() { return taskMapper.findAllTasks();}

    @Override
    public Task findById(int id) {
        return taskMapper.findById(id);
    }

    @Override
    public List<Task> findAllByPage(String title, String action, Pageable pageable) {
        return taskMapper.findAllByPage(title, action, pageable);
    }

    @Override
    public int countTasks(String title, String action) {
        return taskMapper.countTasks(title, action);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public void deleteTask(int id) {
        taskMapper.deleteTask(id);
    }


}
