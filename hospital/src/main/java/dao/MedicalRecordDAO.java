package dao;

import java.util.List;

import model.MedicalRecord;

public interface MedicalRecordDAO {

    MedicalRecord create(MedicalRecord medicalRecord);

    MedicalRecord update(MedicalRecord medicalRecord);

    MedicalRecord findById(int id);

    List<MedicalRecord> findByPatientId(int patientId);

    List<MedicalRecord> findAll();

    boolean isThereOpen(int patientId);

    void delete(MedicalRecord medicalRecord);

}
