package fr.eni.filmotheque.converter;


import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.util.GenreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter implements Converter<String, Genre> {

    @Autowired
    private GenreService genreService;

    @Override
    public Genre convert(String id) {
        Genre genre = new Genre();
        try {
            genre = genreService.getGenreById((long)Integer.parseInt(id));
        } catch (GenreException msg) {
            msg.printStackTrace();
        }
        return genre;
    }
}
