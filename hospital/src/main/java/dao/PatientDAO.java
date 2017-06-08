package dao;

import java.util.List;

import model.Patient;

public interface PatientDAO {

	Patient create(Patient patient);

	Patient findById(int id);

	List<Patient> findByLastName(String lastName);

	Patient update(Patient patient);

	void delete(int id);

	List<Patient> findAllActive();
	
	List<Patient> findAll();
}
