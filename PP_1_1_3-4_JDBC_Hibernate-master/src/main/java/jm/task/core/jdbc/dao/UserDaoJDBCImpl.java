package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USER(" +
                "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                "NAME VARCHAR(45), LASTNAME VARCHAR(45), " +
                "AGE TINYINT)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USER";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении пользователя в таблицу" + e);
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USER WHERE ID = ?";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка удалении пользователя" + e);
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USER";
        ArrayList<User> listUsers = new ArrayList<>();

        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("ID"));
                    user.setName(resultSet.getString("NAME"));
                    user.setLastName(resultSet.getString("LASTNAME"));
                    user.setAge(resultSet.getByte("AGE"));
                    listUsers.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении коллекции User" + e);
            e.printStackTrace();
        }
        return listUsers;

    }

    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE USER";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка очищении таблицы" + e);
            e.printStackTrace();
        }

    }
}
