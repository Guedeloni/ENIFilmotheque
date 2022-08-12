package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;
import fr.eni.filmotheque.util.MembreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/membres")
public class MembreRestController {
    @Autowired
    private MembreService membreService;

    @GetMapping
    public List<Membre> getMembres() {
        return membreService.getMembreList();
    }

//    @GetMapping("/creation")
//    public String getMembre(Model model) {
//        model.addAttribute("membre", new Membre());
//        return "creationMembre";
//    }

    @PostMapping("/creation")
    public Membre postMembre(@RequestBody Membre membre) throws MembreException {
        membreService.addMembre(membre);
        return membre;
    }
}
