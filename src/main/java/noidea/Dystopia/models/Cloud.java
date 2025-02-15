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

    /*
    Стратегия генерации `IDENTITY`: Используем стратегию IDENTITY для автоматического увеличения значения поля id при добавлении новой записи.

    Такое улучшение сделает вашу модель более понятной и соответствующей лучшим практикам разработки.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String cloudId;

    private String cloudName;

    private String driveLink;

    private String mimeType;

}
