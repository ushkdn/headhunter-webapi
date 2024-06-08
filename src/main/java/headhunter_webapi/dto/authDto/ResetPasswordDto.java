package headhunter_webapi.dto.authDto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ResetPasswordDto(
        @NotEmpty(message = "Code cannot be empty.")
        @Size(min=8, max=8, message = "The code must be 8 characters long.")
        String code,
        @NotEmpty(message = "Password cannot be empty.")
        @Size(min=10, max=20, message = "Password must be between 10 and 20 characters.")
        String password,
        //custom annotation for validating password and confirmPassword (must be equal)
        String confirmPassword
) {
}
