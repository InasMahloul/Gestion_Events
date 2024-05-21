package ma.xproce.events12.service;


import ma.xproce.events12.dao.entities.Participant;
import ma.xproce.events12.dao.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParticipantManagerService {
    @Autowired
    private ParticipantRepository participantRepository;

    public void saveParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    public List<Participant> findAllParticipants() {
        return participantRepository.findAll();
    }
}
