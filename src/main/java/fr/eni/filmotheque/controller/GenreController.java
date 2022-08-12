package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.util.GenreException;
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
@RequestMapping("/admin/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String getGenres(Model model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("genreList", genreService.getGenreList());
        return "creationGenre";
    }

    @PostMapping
    public String postGenre(@Valid Genre genre, BindingResult bindingResult, Model model) throws GenreException {
        if (!bindingResult.hasErrors()) {
            try {
                genreService.addGenre(genre);
                model.addAttribute("message", Message.CREATION_OK.showMsg());
            } catch (GenreException msg) {
                msg.printStackTrace();
                model.addAttribute("message", msg.getMessage());
            }
        }
        model.addAttribute("genreList", genreService.getGenreList());
        return "creationGenre";
    }
}
