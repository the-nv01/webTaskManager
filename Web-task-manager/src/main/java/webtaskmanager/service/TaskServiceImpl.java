package webtaskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webtaskmanager.model.Task;
import webtaskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        taskRepository.insert(task);
    }

    @Override
    public Task getTaskByCode(int id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks(String title, String action, Pageable pageable) {
        return taskRepository.findAllByPage(title, action, pageable.getOffset(), pageable.getPageSize());
    }

    @Override
    public int countTasks() {
        return taskRepository.countTasks();
    }

    @Override
    public void editTask(int id, Task task) {
        taskRepository.update(id, task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.delete(id);
    }


}
