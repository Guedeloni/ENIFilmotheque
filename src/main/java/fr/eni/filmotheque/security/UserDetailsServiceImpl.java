package fr.eni.filmotheque.security;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;
import fr.eni.filmotheque.util.MembreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    Membre membre = new Membre();
    @Autowired
    private MembreService membreService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Creation d'un admin via le constructeur au demarrage de l'appli.
    public UserDetailsServiceImpl(MembreService membreService) throws MembreException {
        this.membreService = membreService;
        try {
            membreService.addMembre(new Membre("admin", "admin", "", true));
        } catch (Exception msg) {
            msg.printStackTrace();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        try {
            membre = membreService.getMembreByLogin(login);
            if (membre == null) throw new UsernameNotFoundException(login);
            else return new Utilisateur(membre);
        } catch (MembreException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(login);
        }
    }
}
