package ua.nure.silin.spring5recipeapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;
import ua.nure.silin.spring5recipeapp.repository.UnitOfMeasureRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUnitOfMeasureServiceTest {

    private static final String TEASPOON = "teaspoon";
    private static final String TABLESPOON = "tablespoon";
    private static final String UNIT = "unit";

    @InjectMocks
    private DefaultUnitOfMeasureService defaultUnitOfMeasureService;
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void shouldReturnOptionalWithUnitOfMeasureOfTheGivenNameFromListIfExists() {
        //given
        List<UnitOfMeasure> existingMeasureUnits = prepareMeasureUnits();
        //when
        Optional<UnitOfMeasure> unitOfMeasureOpt = defaultUnitOfMeasureService
                .getMeasureUnitFromListByName(existingMeasureUnits, TEASPOON);
        //then
        assertThat(unitOfMeasureOpt.get().getDescription()).isEqualTo(TEASPOON);
    }

    @Test
    public void shouldReturnEmptyOptionalFromListIfUnitOfMeasureWithParticularNameDoesNotExist() {
        //given
        List<UnitOfMeasure> existingMeasureUnits = prepareMeasureUnits();
        //when
        Optional<UnitOfMeasure> unitOfMeasureOpt = defaultUnitOfMeasureService
                .getMeasureUnitFromListByName(existingMeasureUnits, UNIT);
        //then
        assertThat(unitOfMeasureOpt).isEmpty();
    }

    private List<UnitOfMeasure> prepareMeasureUnits() {
        return asList(new UnitOfMeasure(TEASPOON), new UnitOfMeasure(TABLESPOON));
    }
}