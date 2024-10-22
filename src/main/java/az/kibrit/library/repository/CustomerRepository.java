package az.kibrit.library.repository;
import az.kibrit.library.model.entity.Book;
import az.kibrit.library.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    boolean existsByFirstNameAndLastName(String firstName, String lastName);
//
//    @Query("SELECT c.startDates, c.endDates FROM Customer c WHERE c.id = :customerId")
//    List<Map<Book, LocalDate>> findBookReadingDatesByCustomerId(@Param("customerId") Long customerId);
}
