package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
public class AvisServiceImplJpa implements AvisService {

    @Autowired
    AvisRepository avisRepository;

    @Override
    public void addAvis(Avis avis) {
        avisRepository.save(avis);
    }
}
