package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Membre;
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
@Profile("dev")
public class MembreServiceImplDev implements MembreService {
    private List<Membre> membreList = new ArrayList<>();

    // Simulation d'enregistrement existant ds. le constructeur du service
    private MembreServiceImplDev(PasswordEncoder passwordEncoder) {
        Membre membre_01   = new Membre("Gégé", passwordEncoder.encode("gegesecret"),
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.ZUbWCu1lOYZr9rSdfGuh6AHaHa%26pid%3DApi&f=1",
                            true
                            );
        Membre membre_02   = new Membre("Lulu", passwordEncoder.encode("lulusecret"),
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.__CGvHjQnyVK_sYvozfC4gAAAA%26pid%3DApi&f=1",
                            false
                            );
        Membre membre_03   = new Membre("Coco", passwordEncoder.encode("cocosecret"),
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.h2RFIksj-fUWPWxIwM07tgHaHa%26pid%3DApi&f=1",
                            false
                            );
        membreList.add(membre_01);
        membreList.add(membre_02);
        membreList.add(membre_03);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Membre> getMembreList() {return membreList;}

    @Override
    public Membre getMembreByLogin(String login) throws MembreException {
        Membre membre = null;
        for (Membre g : membreList) {
            if (g.getLogin().equals(login)) {
                membre = g;
                break;
            }
        }
        if (membre == null) throw new MembreException(Message.MEMBRE_NOT_EXIST.showMsg());
        return membre;
    }

    @Override
    public void addMembre(Membre membre) throws MembreException {
        long sizeOfList = membreList.size();
        String loginNormalise = Util.normalizeStringToCapitalize(membre.getLogin());

        // Verification d'existance ou non
        for (Membre f : membreList) {
            if (f.getLogin().equals(loginNormalise)) {
                throw new MembreException(Message.MEMBRE_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau membre
        membre.setLogin(loginNormalise);
        membre.setPassword(passwordEncoder.encode(membre.getPassword()));
        membreList.add(membre);
    }
}
