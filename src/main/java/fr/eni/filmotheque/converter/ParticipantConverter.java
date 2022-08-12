package fr.eni.filmotheque.converter;


import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.ParticipantService;
import fr.eni.filmotheque.util.ParticipantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter implements Converter<String, Participant> {

    @Autowired
    private ParticipantService participantService;

    @Override
    public Participant convert(String id) {
        Participant participant = new Participant();
        try {
            participant = participantService.getParticipantById((long)Integer.parseInt(id));
        } catch (ParticipantException msg) {
            msg.printStackTrace();
        }
        return participant;
    }
}
