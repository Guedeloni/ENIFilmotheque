package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.service.AvisService;
import fr.eni.filmotheque.service.FilmService;
import fr.eni.filmotheque.util.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/films/{id}/avis")       // <= "route imbriquee". Bonne pratique pour une ressource dependante
                                                // d'une autre (ici, creation d'un avis a partir d'un film)
public class AvisRestController {

    @Autowired
    private AvisService avisService;
    @Autowired
    private FilmService filmService;

    @PostMapping("/creation")
    public Avis postAvis(@PathVariable long id, @RequestBody Avis avis) throws FilmException {
        try {
            Film film = filmService.getFilmById(id);
            avis.setFilm(film);
            avisService.addAvis(avis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avis;
    }

}
