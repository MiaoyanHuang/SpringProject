package hmy.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {
    //private int id;
    //private String email;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    //private byte[] avatar;
    //private String firstName;
    //private String lastName;
    //private String identity;
    //private String gender;
}
