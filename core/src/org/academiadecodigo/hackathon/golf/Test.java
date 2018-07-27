package org.academiadecodigo.hackathon.golf;


import com.badlogic.gdx.Gdx;

/**
 * Created by codecadet on 27/07/2018.
 */
public class Test {

    public static void main(String[] args) {

        Persistence persistence = new Persistence();
        persistence.createConnection();
        persistence.insertScore("sadf", 234);
        persistence.close();
    }

}
