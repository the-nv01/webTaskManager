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
@RequiredArgsConstructor
public class TaskServiceimpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskByCode(String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task editTask(String id, Task task) {
        Task t = taskRepository.findById(id).get();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setAction(task.getAction());
        return taskRepository.save(t);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Page<Task> search(String title, String action, Pageable pageable) {
        return taskRepository.findAllByTitleContainingAndActionContaining(title, action, pageable);
    }

}
