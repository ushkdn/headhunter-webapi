package headhunter_webapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Vacancy")
public class Vacancy {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private Company company;
   @Column(name="Title")
    private String title;
    @Column(name="Description")
    private String description;
    @Column(name="MinSalary")
    private Integer minSalary;
    @Column(name="MaxSalary")
    private Integer maxSalary;
    @Column(name="MinYearsOfExperience")
    private Byte minYearsOfExperience;
    @Column(name="MaxYearsOfExperience")
    private Byte maxYearsOfExperience;
    @Column(name="PublicationDate")
    private LocalDate publicationDate;

}
