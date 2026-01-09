package fr.eni.demo.sql;

import fr.eni.demo.bo.formation.Cours;
import fr.eni.demo.dal.CoursRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestSQL {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void test_findByFiliereSQL(){

        Cours cours = Cours
                .builder()
                .titre("testTitre")
                .reference("testReference")
                .duree(10)
                .filiere("Dev")
                .build();
        Cours cours2 = Cours
                .builder()
                .titre("testTitre2")
                .reference("testReference2")
                .duree(5)
                .filiere("Dev")
                .build();

        Cours cours3 = Cours
                .builder()
                .titre("testTitre3")
                .reference("testReference3")
                .duree(15)
                .filiere("Reseau")
                .build();

        testEntityManager.persist(cours);
        testEntityManager.persist(cours2);
        testEntityManager.persist(cours3);
        testEntityManager.flush();

        List<Cours> listeCours = coursRepository.findByFiliereSQL("Dev");

        log.info(listeCours.toString());
        assertThat(listeCours).hasSize(2);

    }


}
