package dao;

import java.util.List;

import model.PrescriptionType;

public interface PrescriptionTypeDAO {
	PrescriptionType create(PrescriptionType prescriptionType);

	PrescriptionType findById(int id);

	PrescriptionType update(PrescriptionType prescriptionType);

	void delete(int id);

	List<PrescriptionType> findAll();

    PrescriptionType findByType(String type);
}
