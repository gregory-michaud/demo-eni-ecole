package fr.eni.demo.dal;

import fr.eni.demo.bo.stagiaire.EtudiantEni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantEniRepository extends JpaRepository<EtudiantEni,String> {
}
