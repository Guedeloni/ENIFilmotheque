package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.ParticipantException;
import fr.eni.filmotheque.util.Util;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class ParticipantServiceImplDev implements ParticipantService {
    private List<Participant> participantList = new ArrayList<>();

    // Simulation d'enregistrement existant ds. le constructeur du service
    private ParticipantServiceImplDev() {
        Participant participant_01   = new Participant(1, "Sidney", "Lumet", false, true,
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.gTboKn_33chTWhfc467ykQHaK3%26pid%3DApi&f=1"
                            );
        Participant participant_02   = new Participant(2, "Henry", "Fonda", true, false,
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.v5OJOsM1-5T4q_PvaWfkLQHaJ_%26pid%3DApi&f=1"
                            );
        Participant participant_03   = new Participant(3, "Martin", "Balsam", true, false,
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.8qT7C7KCc7P-4ruVehM8BQHaIz%26pid%3DApi&f=1"
        );
        participantList.add(participant_01);
        participantList.add(participant_02);
        participantList.add(participant_03);
    }

    @Override
    public List<Participant> getParticipantList() {return participantList;}

    @Override
    public Participant getParticipantById(long id) throws ParticipantException {
        Participant participant = new Participant();
        participant = null;
        for (Participant g : participantList) {
            if (g.getId() == id) {
                participant = g;
                break;
            }
        }
        if (participant == null) throw new ParticipantException(Message.PARTICIPANT_NOT_EXIST.showMsg());
        return participant;
    }

    @Override
    public void addParticipant(Participant participant) throws ParticipantException {
        long sizeOfList = participantList.size();
        String nomNormalise = Util.normalizeStringToCapitalize(participant.getNom());
        String prenomNormalise = Util.normalizeStringToCapitalize(participant.getPrenom());

        // Verification d'existance ou non
        for (Participant f : participantList) {
            if (f.getNom().equals(nomNormalise) & f.getPrenom().equals(prenomNormalise)) {
                throw new ParticipantException(Message.PARTICIPANT_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau participant
        participant.setId(sizeOfList+=1);
        participant.setNom(nomNormalise);
        participant.setPrenom(prenomNormalise);
        participantList.add(participant);
    }
}
