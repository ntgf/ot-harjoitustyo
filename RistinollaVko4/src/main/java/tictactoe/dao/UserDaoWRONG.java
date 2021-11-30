package tictactoe.dao;

import java.util.List;
import tictactoe.domain.User;

public interface UserDaoWRONG {
    
    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();
}
