//package webtaskmanager.repository;
//
//import org.apache.ibatis.annotations.*;
//import org.springframework.data.annotation.QueryAnnotation;
//import org.springframework.data.domain.Pageable;
//import webtaskmanager.model.Task;
//
//import java.util.List;
//
//@Mapper
//public interface TaskRepository {
//
//    @Select("SELECT * FROM task")
//    public List<Task> findAll();
//
//    @Select("SELECT * FROM task WHERE title LIKE %#{title}% " +
//            " AND action LIKE %#{action}%" +
//            " LIMIT #{offset}, #{pageSize}")
//    public List<Task> findAllByPage(String title, String action, long offset, int pageSize);
//
//    @Select("SELECT * FROM task WHERE id=#{id}")
//    public Task findById(int id);
//
//    @Delete("DELETE FROM task WHERE id=#{id}")
//    public void delete(int id);
//
//    @Insert("INSERT INTO task(title, description, action) " +
//            " VALUES (#{task.getTitle()}, #{task.getDescription()}, #{task.getAction()})")
//    public void insert(Task task);
//
//    @Update("UPDATE task WHERE id=#{id} SET title=#{task.getTitle()}, " +
//            " description=#{task.getDescription()}, action=#{task.getAction()}")
//    public void update(int id, Task task);
//
//    @Select("SELECT count(*) FROM task")
//    public int countTasks();
//
//}
