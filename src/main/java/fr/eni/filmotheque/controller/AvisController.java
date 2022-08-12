package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.security.Utilisateur;
import fr.eni.filmotheque.service.AvisService;
import fr.eni.filmotheque.service.FilmService;
import fr.eni.filmotheque.util.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/prive/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;
    @Autowired
    private FilmService filmService;

    @GetMapping("/creation")
    public String getAvis(long id, Model model) throws FilmException {
        model.addAttribute("avis", new Avis());
        try {
            addFilmToModel(id, model);
        } catch (FilmException msg) {
            toDoWhenCatch(model, msg);
        }
        return "creationAvis";
    }

    @PostMapping("/creation")
    public String postAvis(long filmId, @AuthenticationPrincipal Utilisateur utilisateurConnecte, @Valid Avis avis, BindingResult bindingResult, Model model)
            throws FilmException {
        if (!bindingResult.hasErrors()) {
            Film film;
            try {
                film = filmService.getFilmById(filmId);
                // Ajout de l'avis a la liste des avis du film
                System.out.println("1. AJOUT AVIS A FILM_LIST");
                film.getAvisList().add(avis);
                // Ajout du film (relation bidirectionnelle film/avis)
                System.out.println("2. AJOUT FILM A AVIS");
                avis.setFilm(film);
                // Ajout du membre a l'avis
                System.out.println("3. AJOUT MEMBRE A AVIS");
                avis.setMembre(utilisateurConnecte.getMembre());
                // Ajout de l'avis
                System.out.println("4. AJOUT AVIS A DB");
                avisService.addAvis(avis);
            } catch (FilmException msg) {
                toDoWhenCatch(model, msg);
            }
        } else {
            addFilmToModel(filmId, model);
            return "creationAvis";
        }
        addFilmToModel(filmId, model);
        return "affichageDetailFilm";
    }

    private void addFilmToModel(long id, Model model) throws FilmException {
        model.addAttribute("film", filmService.getFilmById(id));
    }

    private static void toDoWhenCatch(Model model, FilmException msg) {
        msg.printStackTrace();
        model.addAttribute("message", msg.getMessage());
    }


}
