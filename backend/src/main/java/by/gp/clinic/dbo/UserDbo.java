package by.gp.clinic.dbo;

import by.gp.clinic.enumerated.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class UserDbo extends AbstractDbo {

    private String name;

    private String password;

    private UserRole role;
}
