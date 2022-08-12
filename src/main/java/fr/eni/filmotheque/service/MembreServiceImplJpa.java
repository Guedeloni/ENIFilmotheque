package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.repository.MembreRepository;
import fr.eni.filmotheque.util.MembreException;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
public class MembreServiceImplJpa implements MembreService {
    private List<Membre> membreList = new ArrayList<>();

    @Autowired
    private MembreRepository membreRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Membre> getMembreList() {
        return membreRepository.findAll();
    }

    @Override
    public Membre getMembreByLogin(String login) throws MembreException {
        Membre membre = null;
        try {
            membre = membreRepository.findById(login).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (membre == null) throw new MembreException(Message.MEMBRE_NOT_EXIST.showMsg());
        return membre;
    }

    @Override
    public void addMembre(Membre membre) throws MembreException {
        String loginNormalise = Util.normalizeStringToCapitalize(membre.getLogin());

        // Verification d'existance ou non
        membreList = membreRepository.findAll();
        for (Membre m : membreList) {
            if (m.getLogin().equals(loginNormalise)) {
                throw new MembreException(Message.MEMBRE_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau membre
        membre.setLogin(loginNormalise);
        membre.setPassword(passwordEncoder.encode(membre.getPassword()));
        membreRepository.save(membre);
    }
}
