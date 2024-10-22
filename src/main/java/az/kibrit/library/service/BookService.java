package az.kibrit.library.service;
import az.kibrit.library.dto.BookDTO;
import az.kibrit.library.exception.ResourceNotFoundException;
import az.kibrit.library.mapper.BookMapper;
import az.kibrit.library.model.entity.Book;
import az.kibrit.library.repository.AuthorRepository;
import az.kibrit.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired  
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setAuthors(book.getAuthors().stream()
                .map(item -> authorRepository.findById(item.getId()).orElseThrow())
                        .peek(item -> item.getBooks().add(book))
                .collect(Collectors.toList()));
        Book savedBook = bookRepository.save(book);
        return bookMapper.bookToBookDTO(savedBook);
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookMapper.bookToBookDTO(book);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.bookToBookDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
