package ma.xproce.events12.web;

import ma.xproce.events12.dao.entities.Event;
import ma.xproce.events12.dao.entities.Organisateur;
import ma.xproce.events12.service.EventManager;
import ma.xproce.events12.service.EventManagerService;
import ma.xproce.events12.service.OrganisateurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class EventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private OrganisateurManager organisateurManager;

    @Autowired
    private EventManagerService eventManagerService;


    @GetMapping("/addEvent")
    public String addEventForm(Model model) {
        model.addAttribute("organisateurs", organisateurManager.getAllOrganisateurs(0, Integer.MAX_VALUE).getContent());

        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @PostMapping("/addEvent")
    public String addEventSubmit(Model model, @ModelAttribute Event event, @RequestParam("organisateurIds") List<Long> organisateurIds) {
        // Récupérer les organisateurs à partir de leurs IDs
        List<Organisateur> organisateurs = organisateurIds.stream()
                .map(organisateurManager::getOrganisateurById)
                .collect(Collectors.toList());

        event.setOrganisateurs(organisateurs);

        eventManager.addEvent(event);

        return "redirect:/getEventsList";
    }


    @GetMapping("/detailEvent/{id}")
    public String getEventDetails(@PathVariable Long id, Model model) {
        Event event = eventManager.getEventById(id);
        if (event != null) {
            model.addAttribute("event", event);
            List<Organisateur> organisateurs = organisateurManager.getAllOrganisateurs();
            model.addAttribute("organisateurs", organisateurs);
            return "detailEvent";
        } else {
            return "error"; // ou rediriger vers une page d'erreur personnalisée
        }
    }

    @GetMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("id") Long id) {
        Event event = new Event();
        event.setId(id);
        if (eventManager.deleteEvent(event)) {
            return "redirect:/getEventsList";
        }
        return "redirect:/getEventsList";
    }

    @GetMapping("/editEvent")
    public String editEventAction(Model model, @RequestParam(name = "id") Long id) {
        Event event = eventManager.getEventById(id);
        if (event != null) {
            model.addAttribute("eventToBeUpdated", event);
            List<Organisateur> organisateurs = organisateurManager.getAllOrganisateurs();
            model.addAttribute("organisateurs", organisateurs);
            return "updateEvent";
        } else {
            return "error";
        }


    }
    @PostMapping("/editEvent")
    public String editEventSubmit(@ModelAttribute Event event,
                                  @RequestParam(value = "organisateurIds", required = false) List<Long> organisateurIds) {
        eventManager.editEvent(event, organisateurIds);
        return "redirect:/getEventsList";
    }


    @GetMapping("/getEventsList")
    public String getAllEvents(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Event> events;
        if (!keyword.isEmpty()) {
            events = eventManager.searchEvents(keyword, page, size);
        } else {
            events = eventManager.getAllEvents(page, size);
        }

        model.addAttribute("listEvents", events.getContent());
        int totalPages = events.getTotalPages();
        int[] pages = IntStream.rangeClosed(0, totalPages - 1).toArray();
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        return "listEvents";
    }

    @GetMapping("/accueil")
    public String getAccueilEvents(Model model,
                                   @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        List<Event> events = eventManagerService.getAllEvents_1(keyword);

        model.addAttribute("listEvents", events);
        model.addAttribute("keyword", keyword);
        return "accueil";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username, @RequestParam("password") String password) {
        return "redirect:/getEventsList"; // Redirection après une connexion réussie
    }


    @GetMapping("/inscription")
    public String inscription() {
        return "inscription"; }


}
