package service;

import java.util.Date;

import org.apache.log4j.Logger;

import command.CreateMedicalRecordCommand;
import dao.DAOFactory;
import model.MedicalRecord;

public class MedicalRecordService {

    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static final Logger logger = Logger.getLogger(CreateMedicalRecordCommand.class.getName());

    public static MedicalRecord getMedicalRecord(int id) {
        MedicalRecord medicalRecord = daoFactory.getMedicalRecordDAO().findById(id);
        medicalRecord.getPrescriptions().addAll(daoFactory.getPrescriptionDAO().findAllByMedicalRecordId(id));
        medicalRecord.getDiagnoses().addAll((daoFactory.getDiagnosisDAO().findAllByMedicalRecordId(id)));
        return medicalRecord;
    }

    public static MedicalRecord createMedicalRecord(int patientId) {
        if (daoFactory.getMedicalRecordDAO().isThereOpen(patientId)) {
            logger.info("Medical record already open");
            return null;
        }
        MedicalRecord medicalRecord = new MedicalRecord(new Date(), patientId);
        return daoFactory.getMedicalRecordDAO().create(medicalRecord);
    }

    public static MedicalRecord closeMedicalRecord(int medicalRecordId) {
        MedicalRecord medicalRecord = getMedicalRecord(medicalRecordId);
        medicalRecord.setClosed(new Date());
        return updateMedicalRecord(medicalRecord);
    }

    private static MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecord = daoFactory.getMedicalRecordDAO().update(medicalRecord);
        medicalRecord.getPrescriptions()
                .addAll(daoFactory.getPrescriptionDAO().findAllByMedicalRecordId(medicalRecord.getId()));
        medicalRecord.getDiagnoses()
                .addAll((daoFactory.getDiagnosisDAO().findAllByMedicalRecordId(medicalRecord.getId())));
        return medicalRecord;
    }

}
