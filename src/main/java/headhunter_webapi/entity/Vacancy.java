package headhunter_webapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private Company company;
    private String title;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;
    private Byte minYearsOfExperience;
    private Byte maxYearsOfExperience;
    private LocalDate publicationDate;

}
