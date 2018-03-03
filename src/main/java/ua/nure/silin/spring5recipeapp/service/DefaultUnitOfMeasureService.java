package ua.nure.silin.spring5recipeapp.service;

import org.springframework.stereotype.Service;
import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;
import ua.nure.silin.spring5recipeapp.repository.UnitOfMeasureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUnitOfMeasureService implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DefaultUnitOfMeasureService(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public List<UnitOfMeasure> getAllMeasureUnits() {
        return (List<UnitOfMeasure>) unitOfMeasureRepository.findAll();
    }

    @Override
    public Optional<UnitOfMeasure> getMeasureUnitFromListByName(List<UnitOfMeasure> units, String name) {
        return units.stream()
                .filter(unitOfMeasure -> name.equals(unitOfMeasure.getDescription()))
                .findFirst();
    }
}
