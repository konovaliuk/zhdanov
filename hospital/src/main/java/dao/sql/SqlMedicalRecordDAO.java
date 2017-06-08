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
import dao.MedicalRecordDAO;
import model.MedicalRecord;
import model.Patient;

public class SqlMedicalRecordDAO implements MedicalRecordDAO {

    private static final Logger logger = Logger.getLogger(SqlMedicalRecordDAO.class.getName());

    @Override
    public MedicalRecord create(MedicalRecord medicalRecord) {

        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_MEDICAL_RECORD,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, new Timestamp(medicalRecord.getOpened().getTime()));
            statement.setInt(2, medicalRecord.getPatientId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    medicalRecord.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating medical record", e);
        }
        return medicalRecord;
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_MEDICAL_RECORD,
                        Statement.RETURN_GENERATED_KEYS);) {
            statement.setTimestamp(1, new Timestamp(medicalRecord.getClosed().getTime()));
            statement.setInt(2, medicalRecord.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating medical record", e);
        }
        return medicalRecord;
    }

    @Override
    public MedicalRecord findById(int id) {
        MedicalRecord medicalRecord = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_MEDICAL_RECORD_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getTimestamp(3) != null) {
                        medicalRecord = new MedicalRecord(resultSet.getInt(1),
                                new Date(resultSet.getTimestamp(2).getTime()),
                                new Date(resultSet.getTimestamp(3).getTime()), resultSet.getInt(4));
                    } else {
                        medicalRecord = (new MedicalRecord(resultSet.getInt(1),
                                new Date(resultSet.getTimestamp(2).getTime()), resultSet.getInt(4)));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting medical record", e);
        }
        return medicalRecord;
    }

    @Override
    public List<MedicalRecord> findAll() {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_ALL_MEDICAL_RECORDS);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                patientList.add(new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            logger.error("Exception while creating medical records", e);
        }
        return null;
    }

    @Override
    public List<MedicalRecord> findByPatientId(int patientId) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_MEDICAL_RECORDS_BY_PATIENT_ID)) {
            statement.setInt(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getTimestamp(3) != null) {
                        medicalRecords.add(
                                new MedicalRecord(resultSet.getInt(1), new Date(resultSet.getTimestamp(2).getTime()),
                                        new Date(resultSet.getTimestamp(3).getTime()), patientId));
                    } else {
                        medicalRecords.add(new MedicalRecord(resultSet.getInt(1),
                                new Date(resultSet.getTimestamp(2).getTime()), patientId));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating medical records", e);
        }
        return medicalRecords;
    }

    @Override
    public boolean isThereOpen(int patientId) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(Query.FIND_OPEN_MEDICAL_RECORD_BY_PATIENT_ID)) {
            statement.setInt(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while searching open medical record", e);
        }
        return false;
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {

    }

}
