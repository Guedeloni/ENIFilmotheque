package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.repository.GenreRepository;
import fr.eni.filmotheque.util.GenreException;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
public class GenreServiceImplJpa implements GenreService {

    private List<Genre> genreList = new ArrayList<>();

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Genre> getGenreList() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(long id) throws GenreException {
        Genre genre = null;
        try {
            genre = genreRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (genre == null) throw new GenreException(Message.GENRE_NOT_EXIST.showMsg());
        return genre;
    }

    @Override
    public void updateGenre(Genre genre) throws GenreException {
        String libelleNormalise = Util.normalizeStringToUpperCase(genre.getLibelle());
        genre.setLibelle(libelleNormalise);
        genreRepository.save(genre);
    }

    @Override
    public void addGenre(Genre genre) throws GenreException {
        String libelleNormalise = Util.normalizeStringToUpperCase(genre.getLibelle());

        // Verification d'existence ou non
        genreList = getGenreList();
        for (Genre g : genreList) {
            if (g.getLibelle().equals(libelleNormalise)) {
                throw new GenreException(Message.GENRE_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau genre
        genre.setLibelle(libelleNormalise);
        genreRepository.save(genre);
    }

}
