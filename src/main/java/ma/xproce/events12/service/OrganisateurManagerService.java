package ma.xproce.events12.service;

import ma.xproce.events12.dao.entities.Organisateur;
import ma.xproce.events12.dao.repositories.OrganisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisateurManagerService implements OrganisateurManager {

    @Autowired
    private OrganisateurRepository organisateurRepository;

    @Override
    public Organisateur addOrganisateur(Organisateur organisateur) {
        return organisateurRepository.save(organisateur);
    }


    @Override
    public Organisateur getOrganisateurById(Long id) {
        return organisateurRepository.findById(id).orElse(null);
    }

    @Override
    public void editOrganisateur(Organisateur organisateur) {
        organisateurRepository.save(organisateur);
    }

    @Override
    public boolean deleteOrganisateur(Organisateur organisateur) {
        if (organisateurRepository.existsById(organisateur.getId())) {
            organisateurRepository.deleteById(organisateur.getId());
            return true;
        } else {
            System.out.println("The organisateur does not exist");
            return false;
        }
    }

    @Override
    public Page<Organisateur> getAllOrganisateurs(int page, int size) {
        return organisateurRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Organisateur> searchOrganisateurs(String keyword, int page, int size) {
        return organisateurRepository.findByNameContainsOrEmailContainsOrTelephoneContains(keyword, keyword, keyword, PageRequest.of(page, size));
    }


    @Override
    public List<Organisateur> getAllOrganisateurs() {
        return organisateurRepository.findAll();
    }



}
