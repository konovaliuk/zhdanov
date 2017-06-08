package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.User;
import service.UserService;
import service.PatientService;
import util.Parameter;
import util.Page;

public class LoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(Parameter.EMAIL);
        String password = request.getParameter(Parameter.PASSWORD);
        User user = UserService.login(email);
        if (user != null && password.equals(user.getPassword())) {
            request.getSession().setAttribute(Parameter.USER, user);
            logger.info("User authorized: " + user);
            request.setAttribute(Parameter.PATIENTS, PatientService.getAllActivePatients());
            request.setAttribute(Parameter.IS_IN_HOSPITAL, true);
            return Page.MAIN;
        } else {
            logger.info("User didn't authorize");
            return Page.LOGIN;
        }
    }

}
