package ua.nure.silin.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
