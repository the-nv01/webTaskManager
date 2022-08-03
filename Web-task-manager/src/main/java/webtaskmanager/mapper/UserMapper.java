package webtaskmanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import webtaskmanager.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertUser (@Param("user") User user);

    List<User> findAllUsers();

    User selectUserByUsername(String username);
}
