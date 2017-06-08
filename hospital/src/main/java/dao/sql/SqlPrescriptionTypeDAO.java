package dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import dao.ConnectionPool;
import dao.PrescriptionTypeDAO;
import model.PrescriptionType;

public class SqlPrescriptionTypeDAO implements PrescriptionTypeDAO {

    public static final Logger logger = Logger.getLogger(SqlPrescriptionTypeDAO.class.getName());
    private static SqlPrescriptionTypeDAO sqlPrescriptionTypeDAO = null;

    private SqlPrescriptionTypeDAO() {
    }

    public static SqlPrescriptionTypeDAO getInstance() {
        if (sqlPrescriptionTypeDAO == null) {
            return new SqlPrescriptionTypeDAO();
        }
        return sqlPrescriptionTypeDAO;
    }

    @Override
    public PrescriptionType findByType(String type) {

        PrescriptionType prescriptionType = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_PRESCRIPTION_TYPE_BY_TYPE)) {
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    prescriptionType = new PrescriptionType(resultSet.getInt(1), type);
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting prescription type", e);
        }
        return prescriptionType;
    }

    @Override
    public PrescriptionType create(PrescriptionType prescriptionType) {
        return null;
    }

    @Override
    public PrescriptionType findById(int id) {
        return null;
    }

    @Override
    public PrescriptionType update(PrescriptionType prescriptionType) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<PrescriptionType> findAll() {
        return null;
    }

}