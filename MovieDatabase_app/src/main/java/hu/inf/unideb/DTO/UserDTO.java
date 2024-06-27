package hu.inf.unideb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String address;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
