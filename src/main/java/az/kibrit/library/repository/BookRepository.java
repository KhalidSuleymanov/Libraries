package az.kibrit.library.repository;
import az.kibrit.library.model.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query("SELECT b FROM Book b WHERE " +
//            "(:authorName IS NULL OR :authorName MEMBER OF b.authors) AND " +
//            "(:genre IS NULL OR :genre MEMBER OF b.genres) AND " +
//            "(:year IS NULL OR b.publishedDate = :year) AND " +
//            "(:rate IS NULL OR b.rating = :rate) AND " +
//            "(:language IS NULL OR b.language = :language)")
//    List<Book> findBooksByCriteria(@Param("authorName") String authorName,
//                                   @Param("genre") String genre,
//                                   @Param("year") Integer year,
//                                   @Param("rate") Integer rate,
//                                   @Param("language") String language);
}
