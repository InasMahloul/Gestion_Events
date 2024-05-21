package ma.xproce.events12.service;

import ma.xproce.events12.dao.entities.Organisateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganisateurManager {


    public Organisateur addOrganisateur(Organisateur organisateur);

    Organisateur getOrganisateurById(Long id);

    void editOrganisateur(Organisateur organisateur);
    public boolean deleteOrganisateur(Organisateur organisateur);
    Page<Organisateur> getAllOrganisateurs(int page, int size);

    List<Organisateur> getAllOrganisateurs();
    public Page<Organisateur> searchOrganisateurs(String keyword, int page, int size);
}
