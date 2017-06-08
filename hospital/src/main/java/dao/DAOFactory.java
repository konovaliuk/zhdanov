package dao;

import dao.sql.SqlDiagnosisDAO;
import dao.sql.SqlMedicalRecordDAO;
import dao.sql.SqlPatientDAO;
import dao.sql.SqlPrescriptionDAO;
import dao.sql.SqlPrescriptionTypeDAO;
import dao.sql.SqlUserDAO;
import dao.sql.SqlUserTypeDAO;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public UserDAO getUserDAO() {
        return new SqlUserDAO();
    }

    public UserTypeDAO getUserTypeDAO() {
        return new SqlUserTypeDAO();
    }

    public PatientDAO getPatientDAO() {
        return new SqlPatientDAO();
    }

    public MedicalRecordDAO getMedicalRecordDAO() {
        return new SqlMedicalRecordDAO();
    }

    public PrescriptionDAO getPrescriptionDAO() {
        return new SqlPrescriptionDAO();
    }

    public PrescriptionTypeDAO getPrescriptionTypeDAO() {

        return new SqlPrescriptionTypeDAO();
    }

    public DiagnosisDAO getDiagnosisDAO() {
        // TODO Auto-generated method stub
        return new SqlDiagnosisDAO();
    }
}
