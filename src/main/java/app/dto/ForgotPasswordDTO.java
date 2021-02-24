package app.dto;

import javax.validation.constraints.NotBlank;

public class ForgotPasswordDTO {
    @NotBlank(message = "Email is mandatory")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
