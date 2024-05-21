package ma.xproce.events12.service;

import ma.xproce.events12.dao.entities.Participant;

import java.util.List;

public interface ParticipantManager {

    void saveParticipant(Participant participant);
    public List<Participant> findAllParticipants();

}
