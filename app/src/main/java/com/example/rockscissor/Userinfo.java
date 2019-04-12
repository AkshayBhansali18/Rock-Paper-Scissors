package com.example.rockscissor;

public class Userinfo {
    String username,password,turn,score;
    public Userinfo()
    {

    }

    public Userinfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.turn = null;
        this.score =null ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
