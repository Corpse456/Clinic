package by.gp.clinic.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "verification_token")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VerificationTokenDbo extends AbstractDbo {

    private String token;

    private LocalDate expiryDate;
}

