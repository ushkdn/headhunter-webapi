package headhunter_webapi.dto.userDto;

import java.time.LocalDate;

public record GetUserDto(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        LocalDate dateOfBirth,
        String Location
) {
}
