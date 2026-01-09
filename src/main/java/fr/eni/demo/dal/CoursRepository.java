package fr.eni.demo.dal;

import fr.eni.demo.bo.formation.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours,Integer> {

    @Query(value = "SELECT c.* FROM COMPUTER_COURSE c WHERE c.COMPUTER_SCIENCE_COURSE = :filiere", nativeQuery = true)
    List<Cours> findByFiliereSQL(@Param("filiere") String  filiere);

}
