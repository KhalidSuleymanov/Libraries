package az.kibrit.library.mapper;
import az.kibrit.library.dto.BookDTO;
import az.kibrit.library.model.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);
}
