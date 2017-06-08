package command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.Patient;
import service.PatientService;
import util.Parameter;
import util.Page;

public class RegisterPatientCommand implements Command {

    private static final String ERROR_MESSEGE = "Please, check the input data or may be such user is almost registred";
    private static final String ERROR = "error";
    public static final Logger logger = Logger.getLogger(RegisterPatientCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Patient patient = null;
        try {
            patient = new Patient(request.getParameter(Parameter.FIRST_NAME), request.getParameter(Parameter.LAST_NAME),
                    new SimpleDateFormat(Parameter.DATE_FORMAT_PATTERN)
                            .parse(request.getParameter(Parameter.DATE_OF_BIRTH)),
                    request.getParameter(Parameter.PHONE_NUMBER));
        } catch (ParseException e) {
            logger.error("Exception while parsing string", e);
        }
        patient = PatientService.createPatient(patient);
        logger.info(patient);
        if (patient.getId() != null) {
            request.setAttribute(Parameter.PATIENT, patient);
            return Page.PATIENT_INFO;
        }
        request.setAttribute(ERROR, ERROR_MESSEGE);

        return Page.ERROR;
    }

}
