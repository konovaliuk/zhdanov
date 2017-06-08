package command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.Diagnosis;
import model.MedicalRecord;
import model.User;
import service.DiagnosisService;
import service.MedicalRecordService;
import service.PatientService;
import util.Parameter;
import util.Page;

public class MakeDiagnosisCommand implements Command {

    private static final Logger logger = Logger.getLogger(MakeDiagnosisCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute(Parameter.USER);
        int medicalRecordId = Integer.parseInt(request.getParameter(Parameter.MEDICAL_RECORD_ID));
        Diagnosis diagnosis = new Diagnosis(request.getParameter(Parameter.NAME),
                request.getParameter(Parameter.DESCRIPTION), new Date(), medicalRecordId, user.getId(),
                Boolean.valueOf(request.getParameter(Parameter.IS_FINAL)));
        diagnosis = DiagnosisService.makeDiagnosis(diagnosis);
        logger.info("Diagnosis was made: " + diagnosis);
        MedicalRecord medicalRecord = null;
        if (diagnosis.getIsFinal()) {
            medicalRecord = MedicalRecordService.closeMedicalRecord(medicalRecordId);
            logger.info("Medical record closed: " + medicalRecord);
            request.setAttribute(Parameter.PATIENT, PatientService.getPatientById(medicalRecord.getPatientId()));
            return Page.PATIENT_INFO;
        }
        medicalRecord = MedicalRecordService.getMedicalRecord(medicalRecordId);
        request.setAttribute(Parameter.MEDICAL_RECORD, medicalRecord);
        request.setAttribute(Parameter.PATIENT, PatientService.getPatientById(medicalRecord.getPatientId()));
        return Page.MEDICAL_RECORD;

    }

}
