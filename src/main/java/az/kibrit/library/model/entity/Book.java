package az.kibrit.library.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "İSBN cannot be null")
    @NotEmpty(message = "İSBN cannot be empty")
    private String isbn;
    @NotNull(message = "Publisher cannot be null")
    @NotEmpty(message = "Publisher cannot be empty")
    private String publisher;
    @Size(max = 1000, message = "Description must be max 1000 characters")
    private String description;
    private String language;
    private String edition;
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png)$",
            message = "Photo URL must end with .jpg, .jpeg or .png")
    private String coverImageUrl;
    @Min(value = 1, message = "Price must be at least 1")
    @Max(value = 500, message = "Price must be no more than 500")
    private BigDecimal price;
    private int numberOfPages;
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double rating;
    @Min(value = 0, message = "Available copies must be at least 0")
    private int availableCopies;
    private String format;
    private LocalDate publishedDate;

    @ManyToMany(fetch = FetchType.LAZY  , mappedBy = "books")
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<Customer> customers = new ArrayList<>();

    public int getRemainingPages(int numberOfPages, int pagesRead) {
        return numberOfPages - pagesRead;
    }

}
