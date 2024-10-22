package az.kibrit.library.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^\\+\\d{1,3}\\d{9,12}$", message = "Phone number must include a country code and be valid")
    private String phone;
    @PastOrPresent(message = "Reading start date must be in the past or present")
    private LocalDate readingStartDate;
    @FutureOrPresent(message = "Reading end date must be in the future or present")
    private LocalDate readingEndDate;
    private String currentBook;


    @ManyToMany
    @JoinTable(
            name = "customer_books",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "customer_book_readings", joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyJoinColumn(name = "book_id")
    @Column(name = "start_date")
    private Map<Book, LocalDate> bookStartDates = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "customer_book_readings", joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyJoinColumn(name = "book_id")
    @Column(name = "end_date")
    private Map<Book, LocalDate> bookEndDates = new HashMap<>();

}
