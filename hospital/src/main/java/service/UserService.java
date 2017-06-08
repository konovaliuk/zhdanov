package service;

import dao.DAOFactory;

import model.User;
import model.UserType;

public class UserService {

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    public static User login(String email) {
        User user = daoFactory.getUserDAO().findByEmail(email);
        if (user != null) {
            user.setUserType(getUserType(user.getUserType().getId()));
        }
        return user;
    }

    private static UserType getUserType(int userTypeId) {
        return daoFactory.getUserTypeDAO().findById(userTypeId);
    }

    public static User getUser(Integer id) {

        return daoFactory.getUserDAO().findById(id);
    }
}
