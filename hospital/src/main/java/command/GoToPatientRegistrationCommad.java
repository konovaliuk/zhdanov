package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Page;

public class GoToPatientRegistrationCommad implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.PATIENT_REGISTRATION;
    }

}
