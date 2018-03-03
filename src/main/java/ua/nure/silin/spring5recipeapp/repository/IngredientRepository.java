package ua.nure.silin.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
