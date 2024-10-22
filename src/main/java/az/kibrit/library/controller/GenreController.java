package az.kibrit.library.controller;
import az.kibrit.library.dto.GenreDTO;
import az.kibrit.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        GenreDTO savedGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable @Min(1) Long id) {
        GenreDTO genreDTO = genreService.getGenreById(id);
        return ResponseEntity.ok(genreDTO);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genreDTOS = genreService.getAllGenres();
        return ResponseEntity.ok(genreDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@Valid @PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        GenreDTO updatedGenre = genreService.updateGenre(id, genreDTO);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable @Min(1) Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}