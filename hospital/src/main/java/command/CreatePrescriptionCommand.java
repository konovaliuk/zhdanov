package command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.MedicalRecord;
import model.Patient;
import model.Prescription;
import model.PrescriptionType;
import model.User;
import service.MedicalRecordService;
import service.PatientService;
import service.PrescriptionService;
import util.Parameter;
import util.Page;

public class CreatePrescriptionCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreatePrescriptionCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int medicalRecordId = Integer.parseInt(request.getParameter(Parameter.MEDICAL_RECORD_ID));
        User user = (User) request.getSession().getAttribute(Parameter.USER);
        PrescriptionType prescriptionType = PrescriptionService
                .getPrescriptionType(request.getParameter(Parameter.PRESCRIPTION_TYPE));
        Prescription prescription = null;
        try {
            prescription = new Prescription(request.getParameter(Parameter.NAME),
                    request.getParameter(Parameter.DESCRIPTION),
                    new SimpleDateFormat(Parameter.DATE_FORMAT_PATTERN).parse(request.getParameter(Parameter.DATE)),
                    prescriptionType, medicalRecordId, user.getId());
        } catch (ParseException e) {
            logger.error("Exception while parsing date");
        }
        if (prescriptionType.getType().equals(Parameter.SURGERY)) {
            prescription = PrescriptionService.createPrescription(prescription);
            logger.info("Prescription was created: " + prescription);
        } else {
            PrescriptionService.createPrescriptions(prescription,
                    Integer.parseInt(request.getParameter(Parameter.DAYS)),
                    Integer.parseInt(request.getParameter(Parameter.TIMES_PER_DAY)));
        }
        MedicalRecord medicalRecord = MedicalRecordService.getMedicalRecord(medicalRecordId);
        Patient patient = PatientService.getPatientById(medicalRecord.getPatientId());
        request.setAttribute(Parameter.MEDICAL_RECORD, medicalRecord);
        request.setAttribute(Parameter.PATIENT, patient);
        return Page.MEDICAL_RECORD;

    }

}
