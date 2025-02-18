package authserver.dto;

import authserver.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;

}
