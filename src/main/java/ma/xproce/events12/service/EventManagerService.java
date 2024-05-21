package ma.xproce.events12.service;
import jakarta.transaction.Transactional;
import ma.xproce.events12.dao.entities.Event;
import ma.xproce.events12.dao.entities.Organisateur;
import ma.xproce.events12.dao.repositories.EventRepository;
import ma.xproce.events12.dao.repositories.OrganisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventManagerService implements EventManager {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganisateurRepository organisateurRepository;

    @Autowired
    public EventManagerService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public Event addEvent(Event event) {
        if (event.getId() == null) {
            // Génère un nouvel ID pour l'événement
            event.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        }
        return eventRepository.save(event);
    }

    @Override
    public boolean deleteEvent(Event event) {
        if (eventRepository.existsById(event.getId())) {
            eventRepository.deleteById(event.getId());
            return true;
        } else {
            System.out.println("The event does not exist");
            return false;
        }
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void editEvent(Event event, List<Long> organisateurIds) {
        // Récupérer l'événement existant de la base de données
        Event existingEvent = eventRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + event.getId()));

        // Mettre à jour les attributs de l'événement
        existingEvent.setName(event.getName());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setStartDateTime(event.getStartDateTime());
        existingEvent.setEndDateTime(event.getEndDateTime());
        existingEvent.setLocation(event.getLocation());

        // Mettre à jour la liste des organisateurs
        Set<Organisateur> selectedOrganisateurs = new HashSet<>();
        if (organisateurIds != null) {
            for (Long id : organisateurIds) {
                Organisateur organisateur = organisateurRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid organisateur Id:" + id));
                selectedOrganisateurs.add(organisateur);
            }
        }
        existingEvent.getOrganisateurs().retainAll(selectedOrganisateurs);
        existingEvent.getOrganisateurs().addAll(selectedOrganisateurs);

        // Enregistrer l'événement modifié
        eventRepository.save(existingEvent);
    }
    @Override
    public Page<Event> getAllEvents(int page, int size) {
        return eventRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Event> getAllEvents_1(String keyword) {
        if (keyword.isEmpty()) {
            return eventRepository.findAll();
        } else {
            return eventRepository.findByNameContainsOrDescriptionContainsOrEndDateTimeContainsOrStartDateTimeContainsOrLocationContains(keyword, keyword, keyword, keyword, keyword);
        }
    }

    @Override
    public Page<Event> searchEvents(String keyword, int page, int size) {
        return eventRepository.findByNameContainsOrDescriptionContainsOrEndDateTimeContainsOrStartDateTimeContainsOrLocationContains(keyword, keyword, keyword, keyword, keyword, PageRequest.of(page, size));
    }

    public List<Event> searchEvents_1(String keyword) {
        return eventRepository.findByNameContainsOrDescriptionContainsOrEndDateTimeContainsOrStartDateTimeContainsOrLocationContains(keyword, keyword, keyword, keyword, keyword);
    }

}










