package az.kibrit.library.service;
import az.kibrit.library.dto.GenreDTO;
import az.kibrit.library.exception.ResourceNotFoundException;
import az.kibrit.library.mapper.GenreMapper;
import az.kibrit.library.model.entity.Genre;
import az.kibrit.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    public GenreService(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.genreDTOToGenre(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.genreToGenreDto(savedGenre);
    }

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found"));
        return genreMapper.genreToGenreDto(genre);
    }

    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(genreMapper::genreToGenreDto)
                .collect(Collectors.toList());
    }

    public GenreDTO updateGenre(Long id, GenreDTO genreDTO) {
        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Genre not found");
        }
        Genre genre = genreMapper.genreDTOToGenre(genreDTO);
        genre.setId(id);
        Genre updatedGenre = genreRepository.save(genre);
        return genreMapper.genreToGenreDto(updatedGenre);
    }

    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Genre not found");
        }
        genreRepository.deleteById(id);
    }

}