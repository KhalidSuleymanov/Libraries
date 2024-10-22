package az.kibrit.library.dto;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 100, message = "First name must be less than 100 characters")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name must be less than 100 characters")
    private String lastName;
    @PastOrPresent(message = "Reading start date must be in the past or present")
    private LocalDate readingStartDate;
    @PastOrPresent(message = "Reading end date must be in the past or present")
    private LocalDate readingEndDate;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^\\+\\d{1,3}\\d{9,12}$", message = "Phone number must include a country code and be valid")
    private String phone;
    private String currentBook;
}
