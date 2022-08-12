package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;
import fr.eni.filmotheque.util.MembreException;
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
@RequestMapping("/admin/membres")
public class MembreController {
    @Autowired
    private MembreService membreService;

    @GetMapping
    public String getMembres(Model model) {
        model.addAttribute("membreList", membreService.getMembreList());
        return "affichageMembres";
    }

    @GetMapping("/creation")
    public String getMembre(Model model) {
        model.addAttribute("membre", new Membre());
        return "creationMembre";
    }

    @PostMapping("/creation")
    public String postMembre(@Valid Membre membre, BindingResult bindingResult, Model model) throws MembreException {
        if (!bindingResult.hasErrors()) {
            try {
                membreService.addMembre(membre);
                model.addAttribute("message", Message.CREATION_OK.showMsg());
            } catch (MembreException msg) {
                msg.printStackTrace();
                model.addAttribute("message", msg.getMessage());
            }
        }
        return "creationMembre";
    }
}
