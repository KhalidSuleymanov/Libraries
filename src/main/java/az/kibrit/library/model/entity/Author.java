package az.kibrit.library.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Firstname cannot be null")
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstName;
    @NotNull(message = "Lastname cannot be null")
    @NotEmpty(message = "Lastname cannot be empty")
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



    @ManyToMany
    @JoinTable(
            name = "authors_and_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

}
