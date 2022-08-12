package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.util.FilmException;
import fr.eni.filmotheque.util.MembreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class AvisServiceImplDev implements AvisService {
    private List<Avis> avisList = new ArrayList<>();
//    @Autowired
//    private MembreService membreService;
//    @Autowired
//    private FilmService filmService;

    // Simulation d'enregistrement existant ds. le constructeur du service
//    private AvisServiceImplDev() throws MembreException, FilmException {
//        Avis avis_01   = new Avis(1, 5, "Top du top", membreService.getMembreByLogin("Coco"),
//                                    filmService.getFilmById(1));
//        Avis avis_02   = new Avis(2, 4, "Je confirme !",
//                                    membreService.getMembreByLogin("Gégé"),
//                                    filmService.getFilmById(1));
//        avisList.add(avis_01);
//        avisList.add(avis_02);
//    }

    @Override
    public void addAvis(Avis avis) {
        long sizeOfList = avisList.size();
        avis.setId(sizeOfList+=1);
        avisList.add(avis);
    }
}
