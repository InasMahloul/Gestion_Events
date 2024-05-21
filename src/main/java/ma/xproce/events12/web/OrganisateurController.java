package ma.xproce.events12.web;

import ma.xproce.events12.dao.entities.Organisateur;
import ma.xproce.events12.service.OrganisateurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.IntStream;


@Controller

public class OrganisateurController {

    @Autowired
    private OrganisateurManager organisateurManager;

    @GetMapping("/listOrganisateurs")
    public String getAllOrganisateurs(Model model,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "3") int size,
                                      @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Organisateur> organisateurs;
        if (!keyword.isEmpty()) {
            organisateurs = organisateurManager.searchOrganisateurs(keyword, page, size);
        } else {
            organisateurs = organisateurManager.getAllOrganisateurs(page, size);
        }

        model.addAttribute("listOrganisateurs", organisateurs.getContent());
        int totalPages = organisateurs.getTotalPages();
        int[] pages = IntStream.rangeClosed(0, totalPages - 1).toArray();
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        return "listOrganisateurs";
    }



    @GetMapping("/addOrganisateur")
    public String addOrganisateurForm(Model model) {
        model.addAttribute("organisateur", new Organisateur());
        return "addOrganisateur";
    }

    @PostMapping("/addOrganisateur")
    public String addOrganisateurSubmit(@ModelAttribute("organisateur") Organisateur organisateur) {
        organisateurManager.addOrganisateur(organisateur);
        return "redirect:/listOrganisateurs";
    }

    @GetMapping("/editOrganisateur")
    public String editOrganisateurAction(Model model, @RequestParam(name = "id") Long id) {
        Organisateur organisateur = organisateurManager.getOrganisateurById(id);
        if (organisateur != null) {
            model.addAttribute("organisateurToBeUpdated", organisateur);
            return "updateOrganisateur";
        } else {
            return "error";
        }
    }

    @PostMapping("/editOrganisateur")
    public String editOrganisateurSubmit(@ModelAttribute Organisateur organisateur) {
        organisateurManager.editOrganisateur(organisateur);
        return "redirect:/listOrganisateurs";
    }

    @GetMapping("/deleteOrganisateur")
    public String deleteOrganisateur(@RequestParam("id") Long id) {
        Organisateur organisateur= new Organisateur();
        organisateur.setId(id);
        if (organisateurManager.deleteOrganisateur(organisateur)) {
            return "redirect:/listOrganisateurs";
        }
        return "redirect:/listOrganisateurs";
    }

}
