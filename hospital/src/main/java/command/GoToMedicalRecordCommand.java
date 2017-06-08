package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MedicalRecord;
import model.Patient;
import service.MedicalRecordService;
import service.PatientService;
import util.Parameter;
import util.Page;

public class GoToMedicalRecordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int medicalRecordId = Integer.parseInt(request.getParameter(Parameter.MEDICAL_RECORD_ID));
        MedicalRecord medicalRecord = MedicalRecordService.getMedicalRecord(medicalRecordId);
        Patient patient = PatientService.getPatientById(medicalRecord.getPatientId());
        request.setAttribute(Parameter.MEDICAL_RECORD, medicalRecord);
        request.setAttribute(Parameter.PATIENT, patient);
        return Page.MEDICAL_RECORD;

    }

}
