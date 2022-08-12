package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.util.GenreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/genres")
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenreList();
    }

    @PostMapping
    public Genre postGenre(@RequestBody Genre genre) throws GenreException {
        genreService.addGenre(genre);
        return genre;
    }

    @PutMapping("/{id}")
    public Genre putGenre(@PathVariable Long id, @RequestBody Genre genre) throws GenreException {
        if (id != null) {
            genre.setId(id);
            genreService.updateGenre(genre);
        }
        return genre;
    }
}
