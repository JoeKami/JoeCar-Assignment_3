package com.coderscampus.userloginapplication;

import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

public class MainUserLoginApp {
    public static void main(String[] args) {
        UserService userService = new UserService();

        try {
            User[] users = userService.usersFile(userService.filePath);
            Scanner scanner = new Scanner(System.in);
            int loginAttempts = 0;
            final int MAX_ATTEMPTS = 5;

            while (loginAttempts < MAX_ATTEMPTS) {
                System.out.println("Please enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Please enter your password: ");
                String password = scanner.nextLine();

                if (userService.validateLogin(users, username, password)) {
                    for (User user : users) {
                        if (user.getUsername().equalsIgnoreCase(username)) {
                            System.out.println("Welcome: " + user.getName());
                            return;
                        }
                    }
                } else {
                    System.out.println("Invalid login, please try again");
                    loginAttempts++;
                }
            }
            System.out.println("Too many failed login attempts, you are now locked out.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
