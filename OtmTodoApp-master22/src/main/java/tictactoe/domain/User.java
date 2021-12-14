package tictactoe.domain;

public class User {
    
    private String name;
    private String username;
    private int wins;
    private int losses;
    
    public User(String username, String name) {
        this.name = name;
        this.username = username;
        this.wins = 0;
        this.losses = 0;
    }
    
    public User(String username, String name, int wins, int losses) {
        this.name = name;
        this.username = username;
        this.wins = wins;
        this.losses = losses;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
    
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
    
    public void increaseWins() {
        this.wins++;
    }
    
    public void increaseLosses() {
        this.losses++;
    }
}