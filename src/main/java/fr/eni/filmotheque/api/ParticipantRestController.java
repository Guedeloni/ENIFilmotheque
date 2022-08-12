package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.ParticipantService;
import fr.eni.filmotheque.util.ParticipantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/participants")
public class ParticipantRestController {
    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public List<Participant> getParticipants() {
        return participantService.getParticipantList();
    }

//    @GetMapping("/creation")
//    public String getParticipant(Model model) {
//        model.addAttribute("participant", new Participant());
//        return "creationParticipant";
//    }

    @PostMapping("/creation")
    public Participant postParticipant(@RequestBody Participant participant) throws ParticipantException {
        participantService.addParticipant(participant);
        return participant;
    }
}
