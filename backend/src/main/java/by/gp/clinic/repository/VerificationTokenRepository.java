package by.gp.clinic.repository;

import by.gp.clinic.dbo.VerificationTokenDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenDbo, Long> {

    void deleteByExpiryDateIsLessThan(LocalDate now);

    Optional<VerificationTokenDbo> findByToken(String token);

    boolean existsByToken(String token);
}
