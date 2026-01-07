package fr.eni.demo.dal;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonneesPersoRepository extends JpaRepository<DonneesPerso,Integer> {
}
