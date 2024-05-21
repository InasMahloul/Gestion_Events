package ma.xproce.events12.dao.repositories;

import ma.xproce.events12.dao.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByNameContainsOrDescriptionContainsOrEndDateTimeContainsOrStartDateTimeContainsOrLocationContains(String keyword, String keyword1, String keyword2, String keyword3, String keyword4, PageRequest of);

    List<Event> findByNameContainsOrDescriptionContainsOrEndDateTimeContainsOrStartDateTimeContainsOrLocationContains(String keyword, String keyword1, String keyword2, String keyword3, String keyword4);
}
