package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import util.Page;

public class LogoutCommand implements Command {

    private static final Logger logger = Logger.getLogger(LogoutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        logger.info("User log out");
        return Page.LOGIN;
    }

}
