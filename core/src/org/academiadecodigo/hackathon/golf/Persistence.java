package org.academiadecodigo.hackathon.golf;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 27/07/2018.
 */
public class Persistence {

    private Connection dbConnection;


    public void createConnection() {

        try {
            if (dbConnection == null) {
                dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toyscores", "root", "");
            }
        } catch (SQLException x) {
            System.out.println("Couldn’t get connection!");
        }

    }

    public List<User> getHighScores() {

        List<User> highScores = new LinkedList<>();

        try {
            Statement statement = dbConnection.createStatement();

            String query = "SELECT name, score FROM scores ORDER BY score DESC LIMIT 10";

            ResultSet resultSet = statement.executeQuery(query);

            for (int i = 0; i < 10; i++) {
                User user = new User();
                resultSet.next();
                user.setName(resultSet.getString(1));
                user.setScore(resultSet.getInt(2));
                highScores.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return highScores;

    }

    public void close() {
        try {
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

}
