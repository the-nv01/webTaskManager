package webtaskmanager.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import webtaskmanager.model.Task;
import webtaskmanager.model.User;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM user")
    public List<User> findAll();

    @Select("SELECT * FROM user WHERE username=#{username}")
    public User findByUsername(String username);

    @Insert("INSERT INTO user(username, password) " +
            " VALUES (#{user.getUsername}, #{user.getPassword}")
    public void insert(User user);
}
