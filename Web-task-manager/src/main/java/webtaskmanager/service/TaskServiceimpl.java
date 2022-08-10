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
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskByCode(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task editTask(int id, Task task) {
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
        return taskRepository.findAllByTitleContainingAndActionContainingOrderByIdAsc(title, action, pageable);
    }

}
