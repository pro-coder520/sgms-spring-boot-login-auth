package ng.edu.unilag.auth.payloads.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Object getUsername() {
        return username;
    }

    public Object getPassword() {
        return password;
    }
}
