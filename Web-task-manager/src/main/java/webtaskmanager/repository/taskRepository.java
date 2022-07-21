package webtaskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webtaskmanager.model.task;

@Repository
public interface taskRepository extends JpaRepository<task, String> {

}
