package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();

        userDao.saveUser("Daniel", "Radcliffe", (byte) 33);
        userDao.saveUser("Timothee", "Chalamet", (byte) 27);
        userDao.saveUser("Chris", "Pratt", (byte) 43);
        userDao.saveUser("Chris", "Evans", (byte) 41);

        userDao.removeUserById(3);

        for (User user : userDao.getAllUsers()) {
            System.out.println(user);
        }

//        userDao.cleanUsersTable();


    }
}
