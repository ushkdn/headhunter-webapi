package headhunter_webapi.dto.authDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterUserDto(
        @NotEmpty(message = "First name cannot be empty.")
        String firstName,
        @NotEmpty(message = "Last name cannot be empty.")
        String lastName,
        @Email(message = "Email is not valid.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotEmpty(message = "Email cannot be empty.")
        String email,
        @NotNull(message = "Phone number cannot be empty.")
        String phoneNumber,
        @NotEmpty(message = "Password cannot be empty.")
        @Size(min=10, max=20, message = "Password must be between 10 and 20 characters.")
        String password,
        @Past(message = "Year of birth must be less than the current one.")
        @NotNull(message = "Date of birth cannot be empty.")
        LocalDate dateOfBirth,
        @NotEmpty(message = "Location cannot be empty.")
        String location
) {
}
