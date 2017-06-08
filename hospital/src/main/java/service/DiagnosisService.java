package service;

import dao.DAOFactory;
import model.Diagnosis;

public class DiagnosisService {

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    public static Diagnosis makeDiagnosis(Diagnosis diagnosis) {
        if (daoFactory.getDiagnosisDAO().findFinalByMedicalRecordId(diagnosis.getMedicalRecordId()) != null) {
            return daoFactory.getDiagnosisDAO().create(diagnosis);
        } else {
            return diagnosis;
        }
    }

}
