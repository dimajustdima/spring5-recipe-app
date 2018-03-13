package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.NotesCommand;
import ua.nure.silin.spring5recipeapp.domain.Notes;

@Component
public class NotesToNotesCommandConverter implements Converter<Notes, NotesCommand> {

    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}
