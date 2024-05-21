package ma.xproce.events12.dao.repositories;

import ma.xproce.events12.dao.entities.Organisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisateurRepository extends JpaRepository<Organisateur, Long> {
    Page<Organisateur> findByNameContainsOrEmailContainsOrTelephoneContains(String keyword, String keyword1, String keyword2, PageRequest of);
}
