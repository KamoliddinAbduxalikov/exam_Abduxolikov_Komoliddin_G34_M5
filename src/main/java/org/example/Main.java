package org.example;

import org.example.domain.User;
import org.example.service.DateService;
import org.example.service.EmailService;
import org.example.service.UserService;
import org.example.service.impl.DateServiceImpl;
import org.example.service.impl.EmailServiceImpl;
import org.example.service.impl.UserServiceImpl;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

public class Main {

    static UserService userService = UserServiceImpl.getInstance();
    static EmailService emailService = EmailServiceImpl.getInstance();
    static DateService dateService = DateServiceImpl.getInstance();
    static Scanner scStr = new Scanner(System.in);
    static Scanner scInt = new Scanner(System.in);
    static Boolean inProcess = true;

    public static void main(String[] args) throws MessagingException, IOException, InterruptedException {
        User user = registerAndLoginMenu();
        if (user != null) {
            userMenu(user);
        }
    }

    private static User registerAndLoginMenu() throws MessagingException {
        while (inProcess) {
            printRegisterAndLoginMenu();

            int userChoice = scInt.nextInt();
            if (userChoice == 0) {
                inProcess = false;
            }

            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter the name : ");
                    String name = scStr.nextLine();

                    System.out.println("Enter the age : ");
                    Integer age = scInt.nextInt();

                    System.out.println("Enter the your email : ");
                    String email = scStr.nextLine();

                    Random random = new Random();
                    Integer msg = 10000 + random.nextInt(90000);
                    emailService.sendEmail(email, msg);

                    boolean process = true;
                    do {
                        System.out.println("Enter the code : ");
                        Integer code = scInt.nextInt();
                        if (code.equals(msg)) {
                            process = false;
                        }
                    } while (process);

                    System.out.println("Enter the password : ");
                    String password = scStr.nextLine();

                    User user = User.builder()
                            .name(name)
                            .age(age)
                            .email(email)
                            .password(password)
                            .build();

                    userService.register(user);

                    return user;
                }
                case 2 -> {
                    System.out.println("Enter the your name : ");
                    String name = scStr.nextLine();

                    System.out.println("Enter the your password : ");
                    String password = scStr.nextLine();

                    return userService.login(name, password);
                }
            }
        }
        return null;
    }


    public static void userMenu(User user) throws MessagingException, IOException, InterruptedException {
        while (true) {
            printUserMenu();

            int userChoice = scInt.nextInt();

            if (userChoice == 0) {
                break;
            }

            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter the your email : ");
                    String email = scStr.nextLine();

                    Random random = new Random();
                    Integer msg = 10000 + random.nextInt(90000);
                    emailService.sendEmail(email, msg);


                    boolean process = true;
                    do {
                        System.out.println("Enter the code : ");
                        Integer code = scInt.nextInt();
                        if (code.equals(msg)) {
                            process = false;
                        }
                    } while (process);

                    System.out.println("Enter the new password : ");
                    String newPassword = scStr.nextLine();

                    userService.changePassword(user.getId(), newPassword);
                }
                case 2 -> {
                    System.out.println("Enter the month : ");
                    Integer month = scInt.nextInt();

                    System.out.println("Enter the day : ");
                    Integer day = scInt.nextInt();

                    dateService.printDateInformation(month, day);
                }
            }
        }
    }

    public static void printUserMenu() {
        System.out.println("""
                1.Change password
                2.Get information about the date
                0.Exit 
                """);
    }

    public static void printRegisterAndLoginMenu() {
        System.out.println("""
                1.Register
                2.Login
                0.Exit
                """);
    }
}