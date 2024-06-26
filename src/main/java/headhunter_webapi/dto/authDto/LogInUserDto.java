package headhunter_webapi.dto.authDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LogInUserDto(
        @Email(message = "Email is not valid.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotEmpty(message = "Email cannot be empty.")
        String email,
        @NotEmpty(message = "Password cannot be empty.")
        @Size(min=10, max=20, message = "Password must be between 10 and 20 characters.")
        String password
)
{}
