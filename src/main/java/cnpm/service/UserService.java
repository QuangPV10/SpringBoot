package cnpm.service;

import java.util.List;
import java.util.Optional;

import cnpm.entity.User;

public interface UserService {

	void saveWithDefaulRole(User user);

	User findById(Long id);

	Optional<User> findByUsername(String username);

	User findByEmail(String email);
	

	void save(User user);

	void changePassword(Optional<User> user, String newPassword);

	List<User> findAllUser();

	void disableUser(String username);
	
	

}
