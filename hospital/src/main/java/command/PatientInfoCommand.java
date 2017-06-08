package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;
import service.PatientService;
import util.Parameter;
import util.Page;

public class PatientInfoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Integer patientId = Integer.parseInt(request.getParameter(Parameter.PATIENT_ID));
        if (patientId != null) {
            Patient patient = PatientService.getPatientById(patientId);
            request.setAttribute(Parameter.PATIENT, patient);
        }
        return Page.PATIENT_INFO;
    }
}
