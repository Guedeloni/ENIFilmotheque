package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.service.FilmService;
import fr.eni.filmotheque.util.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/films")
public class FilmRestController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> getFilms() {
        return filmService.getFilmList();
    }

    @GetMapping("/{titre}")
    public List<Film> getFilmsByTitre(@PathVariable String titre) {
        return filmService.getFilmListByTitre(titre);
    }

    @GetMapping("/detail/{id}")
    public Film getDetailFilm(@PathVariable Long id) throws FilmException {
        return filmService.getFilmById(id);
    }

    @PostMapping("/admin/creation")
    public Film postFilm(@RequestBody Film film) throws FilmException {
        filmService.addFilm(film);
        return film;
    }

}
