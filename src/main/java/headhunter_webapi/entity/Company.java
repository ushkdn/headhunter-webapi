package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name="Description")
    private String description;
    @Column(name="Site")
    private String site;
    @Column(name="Email")
    private String email;
    @Column(name="EmployeesCount")
    private Integer employeesCount;
   // private List<Review> reviews;
    @Column(name="ReviewCount")
    private Integer reviewCount;
    @Column(name="AllMarks")
    private Integer allMarks;
    @Column(name="Rating")
    private Double rating;
    //private List<Vacancy> activeVacancies;

}
