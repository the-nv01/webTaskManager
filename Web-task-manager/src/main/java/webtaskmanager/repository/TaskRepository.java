package webtaskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webtaskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
