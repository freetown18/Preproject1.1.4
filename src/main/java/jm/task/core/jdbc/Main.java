package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        Util.getSessionFactory();

        userService.createUsersTable();
        userService.saveUser("Deangelo", "Ioannidis", (byte)16);
        userService.saveUser("Kaleigh", "Murrufo", (byte)50);
        userService.saveUser("Quintin", "Besen", (byte)92);
        userService.saveUser("Lili", "Delavega", (byte)8);
        userService.getAllUsers();
//        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeSessionFactory();
    }
}
