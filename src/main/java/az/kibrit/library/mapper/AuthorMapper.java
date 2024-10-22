package az.kibrit.library.mapper;
import az.kibrit.library.dto.AuthorDTO;
import az.kibrit.library.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    AuthorDTO authorToAuthorDto(Author author);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
