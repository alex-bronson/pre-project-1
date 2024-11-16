package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alexey", "Khripko", (byte) 24);
        userService.saveUser("Alexander", "Kulishow", (byte) 24);
        userService.saveUser("Salam", "Salamov", (byte) 12);
        userService.saveUser("Babka", "Starogo", (byte) 100);

        userService.removeUserById(3);
        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
