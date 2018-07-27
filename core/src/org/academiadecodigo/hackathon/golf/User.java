package org.academiadecodigo.hackathon.golf;

/**
 * Created by codecadet on 27/07/2018.
 */
public class User {

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }

}
