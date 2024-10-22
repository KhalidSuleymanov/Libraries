package az.kibrit.library.dto;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AuthorDTO {

    private Long id;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Size(max = 30, message = "Nationality must be max 50 characters")
    private String nationality;
    @Size(max = 100, message = "Biography must be max 100 characters")
    private String biography;
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png)$", message = "Photo URL must end with .jpg, .jpeg or .png")
    private String photoUrl;
    @Past(message = "Date of death must be in the past.")
    @Null(message = "If person is not dead, this field must be empty.")
    private LocalDate dateOfDeath;
}
