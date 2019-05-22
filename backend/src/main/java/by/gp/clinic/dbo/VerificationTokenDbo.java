package by.gp.clinic.dbo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "verification_token")
public class VerificationTokenDbo extends AbstractDbo {

    private String token;

    private LocalDate expiryDate;
}

