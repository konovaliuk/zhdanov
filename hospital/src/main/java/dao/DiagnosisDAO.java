package dao;

import java.util.List;

import model.Diagnosis;

public interface DiagnosisDAO {

	Diagnosis create(Diagnosis diagnosis);

	Diagnosis findById(int id);

	Diagnosis update(Diagnosis diagnosis);

	void delete(int id);

	List<Diagnosis> findAll();

    List<Diagnosis> findAllByMedicalRecordId(int medicalRecordId);

    Diagnosis findFinalByMedicalRecordId(Integer medicalRecordId);
}
