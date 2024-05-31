package headhunter_webapi.entity;

import java.time.LocalDate;

public class Vacancy {
    private Long id;
    private Company company;
    private String title;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;
    private byte minYearsOfExperience;
    private byte maxYearsOfExperience;
    private LocalDate dateOfPublication;
}
