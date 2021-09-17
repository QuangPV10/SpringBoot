package cnpm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cnpm.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	User findByEmail(String email);

	@Query(value = "SELECT * FROM user where username = ?1", nativeQuery = true)
	User findNameUser(String username);

	@Query(value = "Select  distinct gender from user", nativeQuery = true)
	List<String> test();
	
	@Query(value ="SELECT COUNT(gender) FROM user where gender = ?1" ,nativeQuery = true)
	int test1(String name);

}
