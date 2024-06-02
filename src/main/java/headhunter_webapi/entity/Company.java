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
    //private List<Review> reviews;
    private Double rating;
   // private List<Vacancy> activeVacancies;

    public Company(Long id, String name, String description, String site, String email, Integer employeesCount, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.site = site;
        this.email = email;
        this.employeesCount = employeesCount;
        //this.reviews = reviews;
        this.rating = rating;
        //this.activeVacancies = activeVacancies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

//    public List<Vacancy> getActiveVacancies() {
//        return activeVacancies;
//    }
//
//    public void setActiveVacancies(List<Vacancy> activeVacancies) {
//        this.activeVacancies = activeVacancies;
//    }
}
