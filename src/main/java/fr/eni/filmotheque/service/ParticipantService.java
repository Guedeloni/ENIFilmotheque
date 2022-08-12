package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.util.ParticipantException;

import java.util.List;

public interface ParticipantService {

    public List<Participant> getParticipantList();
    public Participant getParticipantById(long id) throws ParticipantException;
    public void addParticipant(Participant participant) throws ParticipantException;

}
