package dao;

import java.util.List;

import model.Prescription;

public interface PrescriptionDAO {

    Prescription create(Prescription prescription);

    Prescription findById(int id);

    List<Prescription> findAllByMedicalRecordId(int medicalRecordId);

    Prescription update(Prescription prescription);

    void delete(int id);

    List<Prescription> findAll();

}
