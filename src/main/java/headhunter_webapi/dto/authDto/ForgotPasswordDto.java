package headhunter_webapi.dto.authDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ForgotPasswordDto(
        @Email(message = "Email is not valid.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotEmpty(message = "Email cannot be empty.")
        String email
) {
}
