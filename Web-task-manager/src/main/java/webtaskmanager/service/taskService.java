package webtaskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import webtaskmanager.model.task;
import webtaskmanager.repository.taskRepository;

import java.util.List;

@Service
public class taskService implements taskServiceimpl{

    @Autowired
    taskRepository taskRepository;

    @Override
    public task createTask(task task) {
        return taskRepository.save(task);
    }

    @Override
    public task getTaskByCode(String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<task> getAllTask() {
        return taskRepository.findAll();
    }

    public Page<task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public task editTask(String id, task task) {
        task t = taskRepository.findById(id).get();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setAction(task.getAction());
        return taskRepository.save(t);
    }

    @Override
    public void deleteTask(task task) {
        taskRepository.delete(task);
    }

}
