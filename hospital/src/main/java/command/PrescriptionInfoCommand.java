package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prescription;
import service.PrescriptionService;
import service.UserService;
import util.Parameter;
import util.Page;

public class PrescriptionInfoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int prescriptionId = Integer.parseInt(request.getParameter(Parameter.PRESCRIPTION_ID));
        Prescription prescription = PrescriptionService.getPrescription(prescriptionId);
        request.setAttribute(Parameter.PRESCRIBER, UserService.getUser(prescription.getPrescriberId()));
        if (prescription.getPerformerId() != null) {
            request.setAttribute(Parameter.PERFORMER, UserService.getUser(prescription.getPrescriberId()));
        }
        request.setAttribute(Parameter.PRESCRIPTION, prescription);
        return Page.PRESCRIPTION_INFO;

    }

}
