package headhunter_webapi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String site;
    private String email;
    private Integer employeesCount;
   // private List<Review> reviews;
    private Integer reviewCount;
    private Integer allMarks;
    private Double rating;
    //private List<Vacancy> activeVacancies;

}
