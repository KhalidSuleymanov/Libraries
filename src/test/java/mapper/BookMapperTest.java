package mapper;

import az.kibrit.library.dto.BookDTO;
import az.kibrit.library.mapper.BookMapper;
import az.kibrit.library.model.entity.Book;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookMapperTest {
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Test
    public void testToBookDTO() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setIsbn("1234567890");
        BookDTO bookDTO = bookMapper.bookToBookDTO(book);

        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getIsbn(), bookDTO.getIsbn());

    }

    @Test
    public void testToBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Test Title");
        bookDTO.setIsbn("1234567890");

        Book book = bookMapper.bookDTOToBook(bookDTO);

        assertEquals(bookDTO.getId(), book.getId());
        assertEquals(bookDTO.getTitle(), book.getTitle());
        assertEquals(bookDTO.getIsbn(), book.getIsbn());
    }
}
