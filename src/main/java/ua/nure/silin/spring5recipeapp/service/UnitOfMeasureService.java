package ua.nure.silin.spring5recipeapp.service;

import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;

import java.util.List;
import java.util.Optional;

public interface UnitOfMeasureService {
    List<UnitOfMeasure> getAllMeasureUnits();
    Optional<UnitOfMeasure> getMeasureUnitFromListByName(List<UnitOfMeasure> units, String name);
}
