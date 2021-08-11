package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        //userService.createUsersTable();
        userService.saveUser("Petr", "Petrov", (byte) 20);
        userService.saveUser("Ivan", "Ivanov", (byte) 15);
        userService.saveUser("Kola", "Sidorov", (byte) 30);
        userService.saveUser("Nikita", "Nikitin", (byte) 23);
        //List<User> user = userService.getAllUsers();
        //System.out.println(user.toString());
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
