package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PatientService;
import util.Parameter;
import util.Page;

public class GoToMainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter(Parameter.LAST_NAME) != null) {
            request.setAttribute(Parameter.PATIENTS,
                    PatientService.getPatientByLastName(request.getParameter(Parameter.LAST_NAME)));
        } else if (request.getParameter(Parameter.ALL_PATIETNTS) != null) {
            request.setAttribute(Parameter.PATIENTS, PatientService.getAll());
        } else {
            request.setAttribute(Parameter.PATIENTS, PatientService.getAllActivePatients());
            request.setAttribute("isInHospital", true);
        }
        return Page.MAIN ;
    }

}
