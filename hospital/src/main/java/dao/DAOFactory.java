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
        return SqlUserDAO.getInstance();
    }

    public UserTypeDAO getUserTypeDAO() {
        return SqlUserTypeDAO.getInstance();
    }

    public PatientDAO getPatientDAO() {
        return SqlPatientDAO.getInstance();
    }

    public MedicalRecordDAO getMedicalRecordDAO() {
        return SqlMedicalRecordDAO.getInstance();
    }

    public PrescriptionDAO getPrescriptionDAO() {
        return SqlPrescriptionDAO.getInstance();
    }

    public PrescriptionTypeDAO getPrescriptionTypeDAO() {

        return SqlPrescriptionTypeDAO.getInstance();
    }

    public DiagnosisDAO getDiagnosisDAO() {
        return SqlDiagnosisDAO.getInstance();
    }
}
