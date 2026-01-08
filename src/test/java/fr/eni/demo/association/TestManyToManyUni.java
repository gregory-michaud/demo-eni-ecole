package fr.eni.demo.association;

import fr.eni.demo.bo.formation.Cours;
import fr.eni.demo.bo.formation.Formateur;
import fr.eni.demo.dal.CoursRepository;
import fr.eni.demo.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@DataJpaTest
public class TestManyToManyUni {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    FormateurRepository formateurRepository;

    @Autowired
    CoursRepository coursRepository;

    private List<Cours> listeCoursDB = new ArrayList<>();

    @BeforeEach
    public void initCours() {
        listeCoursDB.add(Cours
                .builder()
                .filiere("Développement")
                .reference("DEV2023-M030")
                .titre("Web Client HTML CSS")
                .duree(5)
                .build());
        listeCoursDB.add(Cours
                .builder()
                .filiere("Développement")
                .reference("DEV2023-M360")
                .titre("Java Frameworks - APIs Web")
                .duree(10)
                .build());
        listeCoursDB.add(Cours
                .builder()
                .filiere("Système et Réseau")
                .reference("TSRR-120")
                .titre("Bases des réseaux")
                .duree(5)
                .build());
        listeCoursDB.add(Cours
                .builder()
                .filiere("Système et Réseau")
                .reference("TSRR-170")
                .titre("GLPI")
                .duree(5)
                .build());

        listeCoursDB.forEach(c -> {
            coursRepository.save(c);
            // Vérification de l'identifiant
            assertThat(c.getId()).isGreaterThan(0);
        });
    }

    @Test
    public void test_save() {
        final Formateur formateur = Formateur
                .builder()
                .nom("BAILLE")
                .prenom("Anne-Lise")
                .email("abaille@campus-eni.fr")
                .immatriculation("ENI_ECOLE_12398")
                .numDom("02XXXXXXXX")
                .filiere("Développement")
                .build();

        // Association ManyToMany
        final List<Cours> coursDev = listeCoursDB
                .stream()
                .filter(item -> item.getFiliere().equals("Développement"))
                .toList();
        assertThat(coursDev).isNotNull();
        assertThat(coursDev).isNotEmpty();
        assertThat(coursDev.size()).isEqualTo(2);
        formateur.setCoursDispenses(coursDev);

        // Appel du comportement
        final Formateur formateurDB = formateurRepository.save(formateur);
        // Vérification de l'identifiant du formateur
        assertThat(formateurDB.getId()).isGreaterThan(0);

        // Vérification de la cascade de l'association
        assertThat(formateurDB.getCoursDispenses()).isNotNull();
        assertThat(formateurDB.getCoursDispenses()).isNotEmpty();
        assertThat(formateurDB.getCoursDispenses().size()).isEqualTo(2);
        log.info(formateurDB.toString());
    }

    @Test
    public void test_delete() {
        final Formateur formateur = Formateur
                .builder()
                .nom("MATHIEU")
                .prenom("Wilfrid")
                .email("wmathieu@campus-eni.fr")
                .immatriculation("ENI_ECOLE_14388")
                .numDom("02XXXXXXXX")
                .numPortable("06XXXXXXXX")
                .filiere("Système et Réseau")
                .build();


        // Association ManyToMany
        final List<Cours> coursRes = listeCoursDB
                .stream()
                .filter(item -> item.getFiliere().equals("Système et Réseau"))
                .toList();
        assertThat(coursRes).isNotNull();
        assertThat(coursRes).isNotEmpty();
        assertThat(coursRes.size()).isEqualTo(2);
        formateur.setCoursDispenses(coursRes);


        // Contexte de la DB
        final Formateur formateurDB = entityManager.persist(formateur);
        entityManager.flush();
        assertThat(formateurDB.getId()).isGreaterThan(0);
        assertThat(formateurDB.getCoursDispenses()).isNotNull();
        assertThat(formateurDB.getCoursDispenses()).isNotEmpty();
        List<Cours> coursDispensesDB = formateurDB.getCoursDispenses();
        assertThat(coursDispensesDB).isNotNull();
        assertThat(coursDispensesDB).isNotEmpty();
        assertThat(coursDispensesDB.size()).isEqualTo(2);



        // Appel du comportement
        formateurRepository.delete(formateurDB);

        // Vérification que l'entité a été supprimée
        final Formateur formateurDB2 = entityManager.find(Formateur.class, formateur.getId());
        assertNull(formateurDB2);

        // Vérifier que tous les cours associés sont toujours en base - pas de suppressions de la table des cours
        List<Cours> lstCoursDB = coursRepository.findAll();
        assertThat(lstCoursDB).isNotNull();
        assertThat(lstCoursDB).isNotEmpty();
        assertThat(lstCoursDB.size()).isEqualTo(4);
    }
}

