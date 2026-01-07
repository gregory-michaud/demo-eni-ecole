package fr.eni.demo.association;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
import fr.eni.demo.bo.stagiaire.Promo;
import fr.eni.demo.dal.EtudiantEniRepository;
import fr.eni.demo.dal.PromoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestOneToMany {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void test_save(){
        Promo promo = Promo
                .builder()
                .nom("PromoTest")
                .build();

        List<EtudiantEni> listeEtudiantEni = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            DonneesPerso donneesPerso = DonneesPerso
                    .builder()
                    .nom("nomTest" + i)
                    .prenom("prenomTest" + i)
                    .numDom("0278451232")
                    .numPortable("0678541265")
                    .emailPersonnel("nomTest" + i + "@gmail.com")
                    .build();
            EtudiantEni etudiantEni  = EtudiantEni
                    .builder()
                    .email("emailTest" + i)
                    .immatriculation("testImmatriculation" + i)
                    .donneesPerso(donneesPerso)
                    .build();

            listeEtudiantEni.add(etudiantEni);
        }

        promo.setEtudiants(listeEtudiantEni);

        Promo promoBDD = promoRepository.save(promo);

        log.info(promoBDD.toString());

        assertThat(promoBDD.getId()).isGreaterThan(0);

        for (int i = 0; i < 3; i++) {
           Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("testImmatriculation" + i);
           assertThat(optionalEtudiantEni.isPresent()).isTrue();
        }
    }

    @Test
    public void test_delete(){
        Promo promo = Promo
                .builder()
                .nom("PromoTest")
                .build();

        List<EtudiantEni> listeEtudiantEni = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            DonneesPerso donneesPerso = DonneesPerso
                    .builder()
                    .nom("nomTest" + i)
                    .prenom("prenomTest" + i)
                    .numDom("0278451232")
                    .numPortable("0678541265")
                    .emailPersonnel("nomTest" + i + "@gmail.com")
                    .build();
            EtudiantEni etudiantEni  = EtudiantEni
                    .builder()
                    .email("emailTest" + i)
                    .immatriculation("testImmatriculation" + i)
                    .donneesPerso(donneesPerso)
                    .build();

            listeEtudiantEni.add(etudiantEni);
        }

        promo.setEtudiants(listeEtudiantEni);

        Promo promoBDD = testEntityManager.persist(promo);
        testEntityManager.flush();

        log.info(promoBDD.toString());

        Integer idPromo = promoBDD.getId();

        promoRepository.delete(promoBDD);

        Optional<Promo> optionalPromo = promoRepository.findById(idPromo);

        assertThat(optionalPromo.isPresent()).isFalse();

        for (int i = 0; i < 3; i++) {
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("testImmatriculation" + i);
            assertThat(optionalEtudiantEni.isPresent()).isFalse();
        }
    }

    @Test
    public void test_orphanRemoval(){
        Promo promo = Promo
                .builder()
                .nom("PromoTest")
                .build();

        List<EtudiantEni> listeEtudiantEni = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            DonneesPerso donneesPerso = DonneesPerso
                    .builder()
                    .nom("nomTest" + i)
                    .prenom("prenomTest" + i)
                    .numDom("0278451232")
                    .numPortable("0678541265")
                    .emailPersonnel("nomTest" + i + "@gmail.com")
                    .build();
            EtudiantEni etudiantEni  = EtudiantEni
                    .builder()
                    .email("emailTest" + i)
                    .immatriculation("testImmatriculation" + i)
                    .donneesPerso(donneesPerso)
                    .build();

            listeEtudiantEni.add(etudiantEni);
        }

        promo.setEtudiants(listeEtudiantEni);

        Promo promoBDD = testEntityManager.persist(promo);
        testEntityManager.flush();

        log.info(promoBDD.toString());

        Integer idPromo = promoBDD.getId();

        promoBDD.getEtudiants().clear();

        promoRepository.delete(promoBDD);

        Optional<Promo> optionalPromo = promoRepository.findById(idPromo);

        assertThat(optionalPromo.isPresent()).isFalse();

        for (int i = 0; i < 3; i++) {
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("testImmatriculation" + i);
            assertThat(optionalEtudiantEni.isPresent()).isFalse();
        }
    }



}
