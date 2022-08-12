package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.util.GenreException;

import java.util.List;

public interface GenreService {
    public void addGenre(Genre genre) throws GenreException;
    public List<Genre> getGenreList();
    public Genre getGenreById(long id) throws GenreException;
//    public Genre getGenreByLibelle(String libelle) throws GenreException;
    public void updateGenre(Genre genre) throws GenreException;
}
