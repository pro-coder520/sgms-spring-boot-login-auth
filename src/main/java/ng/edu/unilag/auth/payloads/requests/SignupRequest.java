package ng.edu.unilag.auth.payloads.requests;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    private Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
