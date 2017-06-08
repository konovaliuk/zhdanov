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
import dao.DiagnosisDAO;
import model.Diagnosis;

public class SqlDiagnosisDAO implements DiagnosisDAO {

    private static final Logger logger = Logger.getLogger(DiagnosisDAO.class.getName());
    private static SqlDiagnosisDAO sqlDiagnosisDAO = null;

    private SqlDiagnosisDAO() {
    }

    public static SqlDiagnosisDAO getInstance() {
        if (sqlDiagnosisDAO == null) {
            return new SqlDiagnosisDAO();
        }
        return sqlDiagnosisDAO;
    }

    @Override
    public Diagnosis create(Diagnosis diagnosis) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_DIAGNOSIS,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, diagnosis.getName());
            statement.setString(2, diagnosis.getDescription());
            statement.setTimestamp(3, new Timestamp(diagnosis.getDate().getTime()));
            statement.setInt(4, diagnosis.getMedicalRecordId());
            statement.setInt(5, diagnosis.getDoctorId());
            statement.setBoolean(6, diagnosis.getIsFinal());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    diagnosis.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating diagnosis", e);
        }
        return diagnosis;
    }

    @Override
    public List<Diagnosis> findAllByMedicalRecordId(int medicalRecordId) {
        List<Diagnosis> diagnoses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_DIAGNOSES_BY_MEDICAL_RECORD)) {
            statement.setInt(1, medicalRecordId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    diagnoses.add(new Diagnosis(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            new Date(resultSet.getTimestamp(4).getTime()), resultSet.getInt(5), resultSet.getInt(6),
                            resultSet.getBoolean(7)));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting diagnoses", e);
        }
        return diagnoses;
    }

    @Override
    public Diagnosis findFinalByMedicalRecordId(Integer medicalRecordId) {
        Diagnosis diagnosis = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_DIAGNOSIS_BY_MEDICAL_RECORD)) {
            statement.setInt(1, medicalRecordId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    diagnosis = new Diagnosis(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            new Date(resultSet.getTimestamp(4).getTime()), resultSet.getInt(5), resultSet.getInt(6),
                            resultSet.getBoolean(7));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting diagnosis", e);
        }
        return diagnosis;
    }

    @Override
    public Diagnosis findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Diagnosis update(Diagnosis diagnosis) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Diagnosis> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
