package webtaskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webtaskmanager.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    Page<Task> findAllByTitleContaining(String title, Pageable pageable);

    Page<Task> findAllByTitleContainingAndActionContaining(String title, String action, Pageable pageable);
}
