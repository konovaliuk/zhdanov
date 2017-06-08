package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Parameter;
import util.Page;

public class GoToNewPrescriptionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute(Parameter.PRESCRIPTION_TYPE, request.getParameter(Parameter.PRESCRIPTION_TYPE));
        request.setAttribute(Parameter.MEDICAL_RECORD_ID, request.getParameter(Parameter.MEDICAL_RECORD_ID));
        return Page.PRESCRIPTION_FORM;

    }

}
