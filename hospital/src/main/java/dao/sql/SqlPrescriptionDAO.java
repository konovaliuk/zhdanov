package dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import dao.ConnectionPool;
import dao.PrescriptionDAO;
import model.Prescription;
import model.PrescriptionType;

public class SqlPrescriptionDAO implements PrescriptionDAO {

    public static final Logger logger = Logger.getLogger(SqlPrescriptionDAO.class.getName());
    private static SqlPrescriptionDAO sqlPrescriptionDAO = null;

    private SqlPrescriptionDAO() {
    }

    public static SqlPrescriptionDAO getInstance() {
        if (sqlPrescriptionDAO == null) {
            return new SqlPrescriptionDAO();
        }
        return sqlPrescriptionDAO;
    }

    @Override
    public Prescription create(Prescription prescription) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_PRESCRIPTION,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, prescription.getName());
            statement.setString(2, prescription.getDescription());
            statement.setTimestamp(3, new Timestamp(prescription.getPerformanceDate().getTime()));
            statement.setInt(4, prescription.getType().getId());
            statement.setInt(5, prescription.getMedicalRecordId());
            statement.setInt(6, prescription.getPrescriberId());
            statement.setBoolean(7, prescription.getIsDone());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    prescription.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating prescription", e);
        }
        return prescription;
    }

    @Override
    public Prescription findById(int id) {
        Prescription prescription = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_PRESCRIPTION_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    prescription = new Prescription(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            new Date(resultSet.getTimestamp(4).getTime()),
                            new PrescriptionType(resultSet.getInt(5), resultSet.getString(11)), resultSet.getInt(6),
                            resultSet.getInt(7), (Integer) resultSet.getObject(8), resultSet.getBoolean(9));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting prescription", e);
        }
        return prescription;
    }

    @Override
    public Prescription update(Prescription prescription) {

        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_PRESCRIPTION,
                        Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, prescription.getPerformerId());
            statement.setBoolean(2, prescription.getIsDone());
            statement.setInt(3, prescription.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating prescription", e);
        }
        return prescription;
    }

    @Override
    public List<Prescription> findAllByMedicalRecordId(int medicalRecordId) {
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(Query.FIND_PRESCRIPTIONS_BY_MEDICAL_RECORD_ID)) {
            statement.setInt(1, medicalRecordId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    prescriptions.add(new Prescription(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), new Date(resultSet.getTimestamp(4).getTime()),
                            new PrescriptionType(resultSet.getInt(5), resultSet.getString(11)), resultSet.getInt(6),
                            resultSet.getInt(7), resultSet.getInt(8), resultSet.getBoolean(9)));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting prescriptions", e);
        }
        return prescriptions;

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Prescription> findAll() {
        return null;
    }

}
