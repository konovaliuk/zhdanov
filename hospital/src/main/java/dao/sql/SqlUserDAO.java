package dao.sql;

import java.sql.Statement;
import java.sql.Timestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dao.ConnectionPool;
import dao.UserDAO;
import model.User;
import model.UserType;

public class SqlUserDAO implements UserDAO {

    public static final Logger logger = Logger.getLogger(SqlUserDAO.class.getName());

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_USER,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setTimestamp(3, new Timestamp(user.getDateOfBirth().getTime()));
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.setInt(7, user.getUserType().getId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating user", e);
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(id, resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4),
                            resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                            new UserType(resultSet.getInt(8)));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting user", e);
        }
        return user;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while deleting user", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_ALL_USERS);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        new UserType(resultSet.getInt(8))));
            }
        } catch (SQLException e) {
            logger.error("Exception while getting all users", e);
        }
        return users;
    }

    @Override
    public User update(User user) {

        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_USER,
                        Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updatating user", e);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6),
                            resultSet.getString(7), new UserType(resultSet.getInt(8)));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting user by email", e);
        }
        return user;
    }

}
