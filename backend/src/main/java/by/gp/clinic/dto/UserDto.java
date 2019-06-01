package by.gp.clinic.dto;

import by.gp.clinic.enumerated.UserRole;
import lombok.Data;

@Data
public class UserDto extends AbstractDto {

    private String alias;

    private String name;

    private String lastName;

    private Long patientId;

    private Long doctorId;

    private UserRole role;

    private boolean enabled;
}
