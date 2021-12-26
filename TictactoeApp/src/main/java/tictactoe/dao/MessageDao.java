package tictactoe.dao;

import java.util.List;
import tictactoe.domain.User;

public interface MessageDao {
    List<String> getLast10Messages(int startindex);
    
    void createNewMessage(User user, String message);
}
