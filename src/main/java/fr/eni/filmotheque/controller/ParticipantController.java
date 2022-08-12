package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.ParticipantService;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.ParticipantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public String getParticipants(Model model) {
        model.addAttribute("participantList", participantService.getParticipantList());
        return "affichageParticipants";
    }

    @GetMapping("/creation")
    public String getParticipant(Model model) {
        model.addAttribute("participant", new Participant());
        return "creationParticipant";
    }

    @PostMapping("/creation")
    public String postParticipant(@Valid Participant participant, BindingResult bindingResult, Model model) throws ParticipantException {
        if (!bindingResult.hasErrors()) {
            try {
                participantService.addParticipant(participant);
                model.addAttribute("message", Message.CREATION_OK.showMsg());
            } catch (ParticipantException msg) {
                msg.printStackTrace();
                model.addAttribute("message", msg.getMessage());
            }
        }
        return "creationParticipant";
    }
}
