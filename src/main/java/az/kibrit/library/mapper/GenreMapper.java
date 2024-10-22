package az.kibrit.library.mapper;
import az.kibrit.library.dto.GenreDTO;
import az.kibrit.library.model.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO genreToGenreDto(Genre genre);
    Genre genreDTOToGenre(GenreDTO genreDTO);

}
