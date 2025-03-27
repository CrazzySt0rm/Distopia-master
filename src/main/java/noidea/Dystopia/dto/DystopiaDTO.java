package noidea.Dystopia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DystopiaDTO {

    @NotBlank(message = "Имя обязательно")
    private String name;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Некорректный номер телефона")
    private String phone;

//    @Size(min = 10, max = 500, message = "Сообщение должно быть от 10 до 500 символов")
    private String message;

    private String gender;
    private String studentCourses;
}
