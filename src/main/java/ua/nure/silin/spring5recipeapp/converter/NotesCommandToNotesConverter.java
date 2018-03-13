package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.NotesCommand;
import ua.nure.silin.spring5recipeapp.domain.Notes;

@Component
public class NotesCommandToNotesConverter implements Converter<NotesCommand, Notes> {

    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        Notes notes = new Notes(source.getRecipeNotes());
        notes.setId(source.getId());
        return notes;
    }
}
