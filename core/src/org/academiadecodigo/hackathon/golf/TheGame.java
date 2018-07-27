package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 26/07/2018.
 */
public class TheGame extends Game{


    public SpriteBatch batch;
    public BitmapFont font;
    private String userName;
    private Persistence persistence;

    private User[] users;


    public void create() {
        populateUsers();
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
        this.persistence = new Persistence();
    }

    public void render(){
        super.render();     //This is necessary
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }

    private void populateUsers() {

        users = new User[10];
        for(int i = 0; i < users.length; i++) {
            users[i] = new User();
            System.out.println("sdfgfda");

        }
        System.out.println(users.length);
        for(User user : users) {

            System.out.println(user);

        }

        users[0].setName("Bruno");
        users[0].setScore(800);
        users[1].setName("Nuno");
        users[1].setScore(750);
        users[2].setName("Nery");
        users[2].setScore(970);
        users[3].setName("Aline");
        users[3].setScore(1200);
        users[4].setName("André");
        users[4].setScore(1010);
        users[5].setName("Bruno");
        users[5].setScore(930);
        users[6].setName("Nuno");
        users[6].setScore(920);
        users[7].setName("Nery");
        users[7].setScore(700);
        users[8].setName("Aline");
        users[8].setScore(890);
        users[9].setName("André");
        users[9].setScore(1030);

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Persistence getPersistence() {
        return persistence;
    }

    public User[] getUsers() {
        return users;
    }
}
