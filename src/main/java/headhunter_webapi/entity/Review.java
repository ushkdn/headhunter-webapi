package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private Company reviewCompany;
    private String title;
    private String description;
    private Double rating;

    public Review(Long id, String title, String description, Double rating) {
        this.id = id;
        //this.reviewCompany = reviewCompany;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Company getReviewCompany() {
//        return reviewCompany;
//    }
//
//    public void setReviewCompany(Company reviewCompany) {
//        this.reviewCompany = reviewCompany;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
