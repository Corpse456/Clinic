package by.gp.clinic.search;

import by.gp.clinic.enumerated.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSearchRequest extends PageableSearchRequest {

    private String alias;

    private UserRole role;
}
