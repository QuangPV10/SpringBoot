package cnpm.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cnpm.entity.Role;
import cnpm.entity.User;
import cnpm.repository.RoleRepository;
import cnpm.repository.UserRepository;
import cnpm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User findById(Long id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.get();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveWithDefaulRole(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(user.getPassword());
		user.setPassword(encodePassword);

		Role role = roleRepository.findByName("User");
		user.addRole(role);
		userRepository.save(user);
	}

	@Override
	public void changePassword(Optional<User> user, String newPassword) {
		String encodedPassword = encoder.encode(newPassword);
		user.get().setPassword(encodedPassword);
		userRepository.save(user.get());
	}

	@Override
	public List<User> findAllUser() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void disableUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		user.get().setEnabled(false);
		userRepository.save(user.get());
	}

}
