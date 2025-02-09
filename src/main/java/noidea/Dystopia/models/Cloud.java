package noidea.Dystopia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cloud")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cloud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cloudId;

    private String cloudName;

    private String driveLink;

    private String mimeType;


}
