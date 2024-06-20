package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Resume")
public class Resume {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;
   // private User owner;
    @Column(name="Speciality")
    private String speciality;
    @Column(name="YearsOfExperience")
    private Byte yearsOfExperience;
    @Column(name="ExpectedSalary")
    private String expectedSalary;
   // private WorkPlan workPlan;
   // private WorkMode workMode;
}
