package cnpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cnpm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
