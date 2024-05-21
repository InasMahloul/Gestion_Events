package ma.xproce.events12.dao.repositories;

import ma.xproce.events12.dao.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findByNameContaining(String keyword);
}
