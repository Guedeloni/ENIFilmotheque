package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.service.FilmService;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.service.ParticipantService;
import fr.eni.filmotheque.util.FilmException;
import fr.eni.filmotheque.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private ParticipantService participantService;

    /**
     * Get avec passage potentiel d'un parametre "titre" pour la fenetre de recherche par titre
     */
    @GetMapping
    public String getFilms(String titre, Model model) {
        if (titre != null) {
            model.addAttribute("filmList", filmService.getFilmListByTitre(titre));
            model.addAttribute("termeRecherche", titre);
        } else {
            model.addAttribute("filmList", filmService.getFilmList());
        }
        return "affichageFilms";
    }

    @GetMapping("/admin/creation")
    public String getFilm(Model model) {
        model.addAttribute("film", new Film());
        alimentationModel(model);
        return "creationFilm";
    }

    @PostMapping("/admin/creation")
    public String postFilm(@Valid Film film, BindingResult bindingResult, Model model) throws FilmException {
        if (!bindingResult.hasErrors()) {
            try {
                filmService.addFilm(film);
                model.addAttribute("message", Message.CREATION_OK.showMsg());
            } catch (FilmException msg) {
                msg.printStackTrace();
                model.addAttribute("message", msg.getMessage());
            }
        }
        alimentationModel(model);
        return "creationFilm";
    }

    @GetMapping("/detail")
    public String getDetailFilm(long id, Model model) throws FilmException {
        model.addAttribute("film", filmService.getFilmById(id));
        return "affichageDetailFilm";
    }

    private void alimentationModel(Model model) {
        model.addAttribute("genreList", genreService.getGenreList());
        model.addAttribute("participantList", participantService.getParticipantList());
    }
}
