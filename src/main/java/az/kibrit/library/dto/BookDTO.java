package az.kibrit.library.dto;
import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO {

    private Long id;
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;
    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^(978|979)\\d{10,13}$", message = "ISBN must be a valid format (e.g., 9781234567890)")
    private String isbn;
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png)$", message = "Cover Image URL must end with .jpg, .jpeg or .png")
    private String coverImageUrl;
    @Positive(message = "Rating must be positive between 1 and 5")
    private Double rating;
    @Positive(message = "Available copies must be positive")
    private int availableCopies;
    @Positive(message = "Number of pages must be positive")
    private int numberOfPages;
    @NotBlank(message = "Format cannot be blank")
    private String format;
    @PastOrPresent(message = "Published date must be in the past or present")
    private LocalDate publishedDate;
    @NotNull(message = "Publisher cannot be null")
    @NotEmpty(message = "Publisher cannot be empty")
    private String publisher;
    @Size(max = 1000, message = "Description must be max 1000 characters")
    private String description;
    private String language;
    private String edition;
    private BigDecimal price;
    private List<AuthorDTO> authors;
    private List<GenreDTO> genres;


}
