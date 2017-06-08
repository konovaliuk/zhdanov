package dao;

import java.util.List;

import model.UserType;

public interface UserTypeDAO {

    UserType create(UserType staffType);

    UserType findById(int id);

    UserType findByType(String type);

    UserType update(UserType staffType);

    void delete(int id);

    List<UserType> findAll();
}
