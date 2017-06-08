package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	User create(User staff);

	User findById(int id);

	User findByEmail(String email);
	
	User update(User staff);

	void delete(int id);

	List<User> findAll();
}
