package by.gp.clinic.repository;

import by.gp.clinic.dbo.VerificationTokenDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenDbo, Long> {

    Optional<VerificationTokenDbo> findByToken(String token);
}
