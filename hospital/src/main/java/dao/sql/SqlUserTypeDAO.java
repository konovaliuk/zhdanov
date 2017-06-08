package dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import dao.ConnectionPool;
import dao.UserTypeDAO;
import model.UserType;

public class SqlUserTypeDAO implements UserTypeDAO {

    public static final Logger logger = Logger.getLogger(SqlUserTypeDAO.class.getName());
    private static SqlUserTypeDAO sqlUserTypeDAO = null;

    private SqlUserTypeDAO() {
    }

    public static UserTypeDAO getInstance() {
        if (sqlUserTypeDAO == null) {
            return new SqlUserTypeDAO();
        }
        return sqlUserTypeDAO;
    }

    @Override
    public UserType create(UserType userType) {
        String sql = "INSERT INTO user_type (type) VALUES (?)";
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userType.getType());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    userType.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating user type", e);
        }
        return userType;
    }

    @Override
    public UserType findById(int id) {
        String sql = "SELECT * FROM user_type WHERE id = ?";
        UserType userType = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userType = new UserType(id, resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting user type", e);
        }
        return userType;
    }

    @Override
    public UserType findByType(String type) {
        String sql = "SELECT * FROM user_type WHERE type = ?";
        UserType userType = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userType = new UserType(resultSet.getInt(1), type);
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting user type", e);
        }
        return userType;
    }

    @Override
    public UserType update(UserType userType) {
        String sql = "UPDATE userType SET type = ? WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, userType.getType());
            statement.setInt(2, userType.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating user type", e);
        }
        return userType;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<UserType> findAll() {
        return null;
    }

}
