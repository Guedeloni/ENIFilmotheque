package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.util.MembreException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface MembreService {

    public List<Membre> getMembreList();
    public Membre getMembreByLogin(String login) throws MembreException;
    public void addMembre(Membre membre) throws MembreException;

}
