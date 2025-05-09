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

    private final Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignupRequest(Set<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<String> getRoles() {
        return Set.of(roles.toString());
    }
}
