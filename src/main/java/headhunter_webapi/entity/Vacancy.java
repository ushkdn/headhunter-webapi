package headhunter_webapi.entity;

import java.time.LocalDate;

public class Vacancy {
    private Long id;
    private Company company;
    private String title;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;
    private Byte minYearsOfExperience;
    private Byte maxYearsOfExperience;
    private LocalDate dateOfPublication;
}
