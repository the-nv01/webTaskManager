package webtaskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import webtaskmanager.model.Task;
import webtaskmanager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceimpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        taskRepository.insert(task);
    }

    @Override
    public Task getTaskByCode(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void editTask(int id, Task task) {
        taskRepository.update(id, task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.delete(id);
    }

    public Page<Task> search(String title, String action, Pageable pageable) {
        return taskRepository.findAllByTitleContainingAndActionContainingOrderByIdAsc(title, action, pageable);
    }

}
