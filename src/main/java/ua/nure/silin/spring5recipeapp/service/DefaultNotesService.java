package ua.nure.silin.spring5recipeapp.service;

import org.springframework.stereotype.Service;
import ua.nure.silin.spring5recipeapp.domain.Notes;
import ua.nure.silin.spring5recipeapp.repository.NotesRepository;

@Service
public class DefaultNotesService implements NotesService {

    private NotesRepository notesRepository;

    public DefaultNotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public Notes save(Notes notes) {
        return notesRepository.save(notes);
    }
}
