package headhunter_webapi.dto.authDto;

import java.time.LocalDate;

public record RegisterUserDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String password,
        LocalDate dateOfBirth,
        String location
) {
}
