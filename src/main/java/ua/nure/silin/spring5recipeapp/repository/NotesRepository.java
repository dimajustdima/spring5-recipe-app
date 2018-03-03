package ua.nure.silin.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.silin.spring5recipeapp.domain.Notes;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
