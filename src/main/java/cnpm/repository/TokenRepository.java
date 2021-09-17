package cnpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cnpm.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
	Token findByConfirmationToken(String confirmationToken);

}