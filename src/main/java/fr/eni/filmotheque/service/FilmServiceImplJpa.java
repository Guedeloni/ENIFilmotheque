package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.repository.FilmRepository;
import fr.eni.filmotheque.util.FilmException;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
public class FilmServiceImplJpa implements FilmService {
    private List<Film> filmList = new ArrayList<>();

    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Film> getFilmList() {
        return filmRepository.findAll();
    }

    @Override
    public List<Film> getFilmListByTitre(String titre) {
        return filmRepository.findByTitreContaining(titre);
    }
    @Override
    public Film getFilmById(long id) throws FilmException {
        Film film = null;
        try {
            film = filmRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (film == null) throw new FilmException(Message.FILM_NOT_EXIST.showMsg());
        return film;
    }

    @Override
    public void addFilm(Film film) throws FilmException {
        String titreNormalise = Util.normalizeStringToUpperCase(film.getTitre());
        LocalDate dateSortie = film.getDateSortie();

        // Verification d'existence ou non
        filmList = getFilmList();
        for (Film f : filmList) {
            if (f.getTitre().equals(titreNormalise) & f.getDateSortie().equals(dateSortie)) {
                throw new FilmException(Message.FILM_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau film
        film.setTitre(titreNormalise);
        filmRepository.save(film);
    }

}
