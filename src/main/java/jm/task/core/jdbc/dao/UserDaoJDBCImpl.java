package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(30), age TINYINT, PRIMARY KEY (id))";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sqlCreate);
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        String sqlDropTable = "DROP TABLE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sqlDropTable);
        } catch (Exception e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSaveUser = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlSaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        String sqlDeleteUser = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlDeleteUser)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sqlListUser = "SELECT * FROM users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlListUser);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                listUser.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {

        }
        return listUser;
    }

    public void cleanUsersTable() {
        String sqlCleanTable = "DELETE FROM users";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute(sqlCleanTable);
        } catch (SQLException throwables) {

        }
    }
}
