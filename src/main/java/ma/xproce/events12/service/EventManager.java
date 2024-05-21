package ma.xproce.events12.service;

import ma.xproce.events12.dao.entities.Event;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventManager {
    public Event addEvent(Event event);
    public boolean deleteEvent(Event event);

    public Event getEventById(Long id);
    //void editEvent(Event event);
    public void editEvent(Event event, List<Long> organisateurIds);

    Page<Event> getAllEvents(int page, int size);

    public List<Event> getAllEvents_1(String keyword);
    public Page<Event> searchEvents(String keyword, int page, int size);

    public List<Event> searchEvents_1(String keyword);
}




