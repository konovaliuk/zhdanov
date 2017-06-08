package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.MedicalRecord;
import model.Patient;
import service.MedicalRecordService;
import service.PatientService;
import util.Parameter;
import util.Page;

public class CreateMedicalRecordCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreateMedicalRecordCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int patientId = Integer.parseInt(request.getParameter(Parameter.PATIENT_ID));
        MedicalRecord medicalRecord = MedicalRecordService.createMedicalRecord(patientId);
        Patient patient = PatientService.getPatientById(patientId);
        request.setAttribute(Parameter.PATIENT, patient);
        if (medicalRecord != null) {
            request.setAttribute(Parameter.MEDICAL_RECORD, medicalRecord);
            logger.info("Medical record was created: " + medicalRecord + "\n for patient: " + patient);
            return Page.MEDICAL_RECORD;
        }
        return Page.PATIENT_INFO;

    }

}
