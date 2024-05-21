package ma.xproce.events12;

import ma.xproce.events12.dao.entities.Event;
import ma.xproce.events12.dao.entities.Organisateur;
import ma.xproce.events12.dao.repositories.EventRepository;
import ma.xproce.events12.dao.repositories.OrganisateurRepository;
import ma.xproce.events12.dao.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Events12Application {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganisateurRepository organisateurRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public static void main(String[] args) {
        SpringApplication.run(Events12Application.class, args);
    }


    @Bean
    CommandLineRunner start() {
        return args -> {


            Event event = new Event();
            event.setId(1L);
            event.setName("Mawazine");
            event.setDescription("Tout le monde bonjour");
            event.setStartDateTime("15/04/2024");
            event.setEndDateTime("17/04/2024");
            event.setLocation("Rabat");
            eventRepository.save(event);

            Event event1 = new Event();
            event1.setId(2L);
            event1.setName("ToooooToooo");
            event1.setDescription("Tout le monde bonsoir");
            event1.setStartDateTime("12/12/2010");
            event1.setEndDateTime("29/01/2010");
            event1.setLocation("Emsi MY");
            eventRepository.save(event1);

            Event event2 = new Event();
            event2.setId(3L);
            event2.setName("Ariiiibaaa");
            event2.setDescription("Salut les amis");
            event2.setStartDateTime("11/02/2023");
            event2.setEndDateTime("22/04/2023");
            event2.setLocation("ENSAB");
            eventRepository.save(event2);

            Organisateur organisateur = new Organisateur();
            organisateur.setId(1L);
            organisateur.setName("Samira");
            organisateur.setEmail("samira@gmail.com");
            organisateur.setTelephone("098765543");
            organisateurRepository.save(organisateur);


        };


    }

}