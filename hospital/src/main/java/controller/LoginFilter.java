package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebFilter({ "/Controller", "*.jsp" })
public class LoginFilter implements Filter {

    public LoginFilter() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        User user = (User) httpRequest.getSession().getAttribute("user");
        String command = (String) httpRequest.getParameter("command");

        if ((session == null || user == null) && !"Login".equals(command)) {
            
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/jsp/Login.jsp");
            dispatcher.forward(httpRequest, httpResponse);
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
