package az.kibrit.library.service;
import az.kibrit.library.dto.AuthorDTO;
import az.kibrit.library.exception.ResourceNotFoundException;
import az.kibrit.library.mapper.AuthorMapper;
import az.kibrit.library.model.entity.Author;
import az.kibrit.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorDto(savedAuthor);
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return authorMapper.authorToAuthorDto(author);
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::authorToAuthorDto)
                .collect(Collectors.toList());
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        author.setId(id);
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorDto(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }
}
