package webtaskmanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import webtaskmanager.model.Task;

import java.util.List;

@Mapper
public interface TaskMapper {

    void insertTask(Task task);

    void updateTask (@Param("id") int id, @Param("task") Task task);

    void deleteTask (int id);

    List<Task> findAllTasks ();

    Task findById (int id);

    int countTasks ();

    List<Task> findAllByPage (@Param("title") String title,
                              @Param("action") String action,
                              @Param("pageable") Pageable pageable);

}
