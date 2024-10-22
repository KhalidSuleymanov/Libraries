package az.kibrit.library.repository;
import az.kibrit.library.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
