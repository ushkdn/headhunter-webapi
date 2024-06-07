package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;
   // private User owner;
    private String speciality;
    private Byte yearsOfExperience;
    private String expectedSalary;
   // private WorkPlan workPlan;
   // private WorkMode workMode;
}
