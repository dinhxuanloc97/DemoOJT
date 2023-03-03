package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="Student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="StudentId")
    private int studentId;
    @Column(name="StudentName",columnDefinition = "nvarchar(50)",nullable = false,unique = true)
    private String studentName;
    @Column(name="StudentAge")
    private int studentAge;
    @Column(name="Status")
    private Boolean studentStatus;

}
