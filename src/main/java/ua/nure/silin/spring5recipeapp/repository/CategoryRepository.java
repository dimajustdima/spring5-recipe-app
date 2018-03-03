package ua.nure.silin.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.silin.spring5recipeapp.domain.Category;

import java.util.Optional;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescriptionIgnoreCase(String description);
    
}
