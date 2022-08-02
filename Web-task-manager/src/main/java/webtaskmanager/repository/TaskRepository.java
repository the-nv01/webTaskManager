package webtaskmanager.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webtaskmanager.model.Task;

import java.util.List;


@Mapper
public interface TaskRepository {

    @Select("SELECT * FROM task")
    public List<Task> findAll();

//    @Select("SELECT  *\n" +
//            "FROM    ( SELECT    ROW_NUMBER() OVER ( ORDER BY id ) AS RowNum\n" +
//            "          FROM      task\n" +
//            "          WHERE     title like #{title} and action LIKE #{action}\n" +
//            "        ) AS RowConstrainedResult\n" +
//            "WHERE   RowNum >= 1\n" +
//            "    AND RowNum <= #{pageable}\n" +
//            "ORDER BY RowNum"
//           )
//    public Page<Task> findAllByTitleContainingAndActionContainingOrderByIdAsc(String title, String action, Pageable pageable);

    @Select("SELECT * FROM task \\n-- #pageable\\n\",\n" +
            "        countQuery=\"SELECT count(*) FROM payment_transactions")
    public Page<Task> findAllByTitleContainingAndActionContainingOrderByIdAsc(String title, String action, Pageable pageable);

    @Select("SELECT * FROM task WHERE id=#{id}")
    public Task findById(int id);

    @Delete("DELETE FROM task WHERE id=#{id}")
    public void delete(int id);

    @Insert("INSERT INTO task(title, description, action) " +
            " VALUES (#{task.getTitle}, #{task.getDescription}, #{task.getAction})")
    public void insert(Task task);

    @Update("UPDATE task WHERE id=#{id} SET title=#{task.getTitle}, " +
            " description=#{task.getDescription}, action=#{task.getAction}")
    public void update(int id, Task task);

}
