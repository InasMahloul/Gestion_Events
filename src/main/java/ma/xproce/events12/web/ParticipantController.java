package ma.xproce.events12.web;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

import jakarta.servlet.http.HttpServletResponse;
import ma.xproce.events12.service.ParticipantManager;
import ma.xproce.events12.service.ParticipantManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ma.xproce.events12.dao.entities.Participant;
import ma.xproce.events12.dao.entities.Event;
import ma.xproce.events12.dao.repositories.ParticipantRepository;
import ma.xproce.events12.dao.repositories.EventRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

@Controller
public class ParticipantController {

    @Autowired
    private ParticipantManagerService participantService;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventRepository eventRepository;


    @GetMapping("/listParticipants")
    public String listParticipants(Model model) {
        List<Participant> listParticipants = participantService.findAllParticipants();
        model.addAttribute("listParticipants", listParticipants);
        return "listParticipants";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    @PostMapping("/saveParticipant")
    public String saveParticipant(Participant participant, Model model) {
        participantService.saveParticipant(participant);
        model.addAttribute("participant", participant);
        model.addAttribute("message", "Participant ajouté avec succès!");
        return "redirect:/success";
    }












}
