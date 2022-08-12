package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.repository.ParticipantRepository;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.ParticipantException;
import fr.eni.filmotheque.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
public class ParticipantServiceImplJpa implements ParticipantService {
    private List<Participant> participantList = new ArrayList<>();

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> getParticipantList() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(long id) throws ParticipantException {
        Participant participant = null;
        try {
            participant = participantRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (participant == null) throw new ParticipantException(Message.PARTICIPANT_NOT_EXIST.showMsg());
        return participant;
    }

    @Override
    public void addParticipant(Participant participant) throws ParticipantException {
        String nomNormalise = Util.normalizeStringToCapitalize(participant.getNom());
        String prenomNormalise = Util.normalizeStringToCapitalize(participant.getPrenom());

        // Verification d'existance ou non
        participantList = getParticipantList();
        for (Participant p : participantList) {
            if (p.getNom().equals(nomNormalise) & p.getPrenom().equals(prenomNormalise)) {
                throw new ParticipantException(Message.PARTICIPANT_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau participant
        participant.setNom(nomNormalise);
        participant.setPrenom(prenomNormalise);
        participantRepository.save(participant);
    }
}
