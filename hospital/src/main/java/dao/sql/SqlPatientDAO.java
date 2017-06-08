package dao.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dao.ConnectionPool;
import dao.PatientDAO;
import model.Patient;

public class SqlPatientDAO implements PatientDAO {

    private static final Logger logger = Logger.getLogger(SqlPatientDAO.class.getName());
    private static SqlPatientDAO sqlPatientDAO = null;

    private SqlPatientDAO() {
    }

    public static PatientDAO getInstance() {
        if (sqlPatientDAO == null) {
            return new SqlPatientDAO();
        }
        return sqlPatientDAO;
    }
    
    
    @Override
    public Patient create(Patient patient) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_PATIENT,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, new Date(patient.getDateOfBirth().getTime()));
            statement.setString(4, patient.getPhoneNumber());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    patient.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while creating patient", e);
        }
        return patient;
    }

    @Override
    public Patient findById(int id) {
        Patient patient = null;
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_PATIENT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = new Patient(id, resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4),
                            resultSet.getString(5));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting patient", e);
        }
        return patient;
    }

    @Override
    public List<Patient> findByLastName(String lastName) {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_PATIENTS_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    patientList.add(new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getDate(4), resultSet.getString(5)));
                }
            }
        } catch (SQLException e) {
            logger.error("Exception while getting patient", e);
        }
        return patientList;
    }

    @Override
    public Patient update(Patient patient) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_PATIENT,
                        Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, new Date(patient.getDateOfBirth().getTime()));
            statement.setString(4, patient.getPhoneNumber());
            statement.setLong(5, patient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating patient", e);
        }
        return patient;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.DELETE_PATIENT)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_ALL_PATIENTS);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                patientList.add(new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            logger.error("Exception while getting patients", e);
        }
        return patientList;
    }

    @Override
    public List<Patient> findAllActive() {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(Query.FIND_ALL_PATIENTS_IN_HOSPITAL);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                patientList.add(new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            logger.error("Exception while getting patients", e);
        }
        return patientList;
    }

   

}
