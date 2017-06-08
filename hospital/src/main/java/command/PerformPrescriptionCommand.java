package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.MedicalRecord;
import model.Patient;
import model.Prescription;
import model.User;
import service.MedicalRecordService;
import service.PatientService;
import service.PrescriptionService;
import util.Parameter;
import util.Page;

public class PerformPrescriptionCommand implements Command {

    private static final Logger logger = Logger.getLogger(PerformPrescriptionCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int prescriptionId = Integer.parseInt(request.getParameter(Parameter.PRESCRIPTION_ID));
        User user = (User) request.getSession().getAttribute(Parameter.USER);
        Prescription prescription = PrescriptionService.performPrescription(prescriptionId, user.getId());
        logger.info("Prescription performed: " + prescription);
        MedicalRecord medicalRecord = MedicalRecordService.getMedicalRecord(prescription.getMedicalRecordId());
        Patient patient = PatientService.getPatientById(medicalRecord.getPatientId());
        request.setAttribute(Parameter.PATIENT, patient);
        request.setAttribute(Parameter.MEDICAL_RECORD, medicalRecord);
        return Page.MEDICAL_RECORD;

    }

}
