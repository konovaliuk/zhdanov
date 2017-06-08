package service;

import java.util.List;

import dao.DAOFactory;
import model.MedicalRecord;
import model.Patient;

public class PatientService {

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    public static Patient createPatient(Patient patient) {
        return daoFactory.getPatientDAO().create(patient);
    }

    public static Patient getPatientById(int patientId) {
        Patient patient = daoFactory.getPatientDAO().findById(patientId);
        List<MedicalRecord> medicalRecords = daoFactory.getMedicalRecordDAO().findByPatientId(patientId);
        patient.getMedicalRecords().addAll(medicalRecords);
        return patient;
    }

    public static List<Patient> getPatientByLastName(String lastName) {
        return daoFactory.getPatientDAO().findByLastName(lastName);
    }

    public static List<Patient> getAllActivePatients() {
        return daoFactory.getPatientDAO().findAllActive();
    }

    public static List<Patient> getAll() {
        return daoFactory.getPatientDAO().findAll();
    }
}
