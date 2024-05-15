package com.coderscampus.userloginapplication;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserService {
    public User[] usersFile (String dataText) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataText));
        int fileLines = 0;
        while (reader.readLine() != null) {
            fileLines++;
        }
        reader.close();

        User[] users = new User[fileLines];
        reader = new BufferedReader(new FileReader(dataText));
        String line;

        int index = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String username = parts[0].trim();
            String password = parts[1].trim();
            String name = parts[2].trim();
            users[index] = new User(username, password, name);
            index++;
        }
        reader.close();
        return users;
    }

    public boolean validateLogin(User[] users, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
