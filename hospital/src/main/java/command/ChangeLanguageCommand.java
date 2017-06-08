package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.PatientService;
import util.Parameter;
import util.Page;

public class ChangeLanguageCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLanguageCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String language = request.getParameter(Parameter.LANGUAGE);
        request.getSession().setAttribute(Parameter.LANGUAGE, language);
        logger.info("Language chaneged to: " + language);
        request.setAttribute(Parameter.PATIENTS, PatientService.getAllActivePatients());
        request.setAttribute("isInHospital", true);
        return Page.MAIN;

    }

}
