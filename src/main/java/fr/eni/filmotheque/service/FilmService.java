package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.util.FilmException;

import java.util.List;

public interface FilmService {

    public List<Film> getFilmList();
    public Film getFilmById(long id) throws FilmException;
    public List<Film> getFilmListByTitre(String titre);
    public void addFilm(Film film) throws FilmException;
}
