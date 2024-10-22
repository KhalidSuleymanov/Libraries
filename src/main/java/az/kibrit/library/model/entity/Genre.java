package az.kibrit.library.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Firstname cannot be null")
    @NotEmpty(message = "Firstname cannot be empty")
    private String name;
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "genres_and_books",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    private List<Book> books = new ArrayList<>();
}
