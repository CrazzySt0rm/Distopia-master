package noidea.Dystopia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dystopia_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dystopia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    private String message;
    private String gender;
    private String studentCourses;

}
