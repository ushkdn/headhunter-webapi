package headhunter_webapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private Company company;
    private String title;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;
    private Byte minYearsOfExperience;
    private Byte maxYearsOfExperience;
    private LocalDate dateOfPublication;

    public Vacancy(Long id, String title, String description, Integer minSalary, Integer maxSalary, Byte minYearsOfExperience, Byte maxYearsOfExperience, LocalDate dateOfPublication) {
        this.id = id;
        //this.company = company;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.minYearsOfExperience = minYearsOfExperience;
        this.maxYearsOfExperience = maxYearsOfExperience;
        this.dateOfPublication = dateOfPublication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Byte getMinYearsOfExperience() {
        return minYearsOfExperience;
    }

    public void setMinYearsOfExperience(Byte minYearsOfExperience) {
        this.minYearsOfExperience = minYearsOfExperience;
    }

    public Byte getMaxYearsOfExperience() {
        return maxYearsOfExperience;
    }

    public void setMaxYearsOfExperience(Byte maxYearsOfExperience) {
        this.maxYearsOfExperience = maxYearsOfExperience;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }
}
