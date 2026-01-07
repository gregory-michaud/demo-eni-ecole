package fr.eni.demo.association;


import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
import fr.eni.demo.dal.DonneesPersoRepository;
import fr.eni.demo.dal.EtudiantEniRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestOneToOneBidirectionnel {

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Autowired
    private DonneesPersoRepository donneesPersoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void test_save(){

        DonneesPerso donneesPerso = DonneesPerso
                .builder()
                .nom("nomTest")
                .prenom("prenomTest")
                .numDom("0278451232")
                .numPortable("0678541265")
                .emailPersonnel("nomTest@gmail.com")
                .build();

        EtudiantEni etudiantEni = EtudiantEni
                .builder()
                .immatriculation("TEST01")
                .email("test@campus-eni.fr")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        EtudiantEni etudiantEniBDD = etudiantEniRepository.save(etudiantEni);

        log.info(etudiantEniBDD.toString());

        assertThat(etudiantEniBDD.getImmatriculation()).isEqualTo(etudiantEni.getImmatriculation());
        assertThat(etudiantEniBDD.getEmail()).isEqualTo(etudiantEni.getEmail());
        assertThat(etudiantEniBDD.getDonneesPerso().getId()).isGreaterThan(0);

    }


    @Test
    public void test_saveDonneesPerso(){

        DonneesPerso donneesPerso = DonneesPerso
                .builder()
                .nom("nomTest")
                .prenom("prenomTest")
                .numDom("0278451232")
                .numPortable("0678541265")
                .emailPersonnel("nomTest@gmail.com")
                .build();

        EtudiantEni etudiantEni = EtudiantEni
                .builder()
                .immatriculation("TEST01")
                .email("test@campus-eni.fr")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        DonneesPerso donneesPersoBDD = donneesPersoRepository.save(donneesPerso);

        log.info(donneesPersoBDD.toString());


        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("TEST01");

        assertThat(optionalEtudiantEni.isPresent()).isTrue();
        assertThat(donneesPersoBDD.getId()).isGreaterThan(0);

    }


    @Test
    public void test_delete(){

        DonneesPerso donneesPerso = DonneesPerso
                .builder()
                .nom("nomTest")
                .prenom("prenomTest")
                .numDom("0278451232")
                .numPortable("0678541265")
                .emailPersonnel("nomTest@gmail.com")
                .build();

        EtudiantEni etudiantEni = EtudiantEni
                .builder()
                .immatriculation("TEST01")
                .email("test@campus-eni.fr")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        EtudiantEni etudiantEniBDD = testEntityManager.persist(etudiantEni);
        testEntityManager.flush();

        log.info(etudiantEniBDD.toString());

        etudiantEniRepository.delete(etudiantEni);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("TEST01");
        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(etudiantEniBDD.getDonneesPerso().getId());

        assertThat(optionalEtudiantEni.isPresent()).isFalse();
        assertThat(optionalDonneesPerso.isPresent()).isFalse();
    }

    @Test
    public void test_deleteDonneesPerso(){

        DonneesPerso donneesPerso = DonneesPerso
                .builder()
                .nom("nomTest")
                .prenom("prenomTest")
                .numDom("0278451232")
                .numPortable("0678541265")
                .emailPersonnel("nomTest@gmail.com")
                .build();

        EtudiantEni etudiantEni = EtudiantEni
                .builder()
                .immatriculation("TEST01")
                .email("test@campus-eni.fr")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        EtudiantEni etudiantEniBDD = testEntityManager.persist(etudiantEni);
        testEntityManager.flush();

        log.info(etudiantEniBDD.toString());

        donneesPersoRepository.delete(etudiantEniBDD.getDonneesPerso());

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("TEST01");
        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(etudiantEniBDD.getDonneesPerso().getId());

        assertThat(optionalEtudiantEni.isPresent()).isFalse();
        assertThat(optionalDonneesPerso.isPresent()).isFalse();
    }

    @Test
    public void test_orphanRemoval(){

        DonneesPerso donneesPerso = DonneesPerso
                .builder()
                .nom("nomTest")
                .prenom("prenomTest")
                .numDom("0278451232")
                .numPortable("0678541265")
                .emailPersonnel("nomTest@gmail.com")
                .build();

        EtudiantEni etudiantEni = EtudiantEni
                .builder()
                .immatriculation("TEST01")
                .email("test@campus-eni.fr")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        EtudiantEni etudiantEniBDD = testEntityManager.persist(etudiantEni);
        testEntityManager.flush();

        log.info(etudiantEniBDD.toString());

        Integer idDonneesPerso = etudiantEniBDD.getDonneesPerso().getId();

        etudiantEniBDD.setDonneesPerso(null);

        etudiantEniRepository.delete(etudiantEni);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("TEST01");
        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);

        assertThat(optionalEtudiantEni.isPresent()).isFalse();
        assertThat(optionalDonneesPerso.isPresent()).isFalse();
    }

    @Test
    public void test_orphanRemoval_donneesPerso(){

        DonneesPerso donneesPerso = DonneesPerso
                .builder()
                .nom("nomTest")
                .prenom("prenomTest")
                .numDom("0278451232")
                .numPortable("0678541265")
                .emailPersonnel("nomTest@gmail.com")
                .build();

        EtudiantEni etudiantEni = EtudiantEni
                .builder()
                .immatriculation("TEST01")
                .email("test@campus-eni.fr")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        EtudiantEni etudiantEniBDD = testEntityManager.persist(etudiantEni);
        testEntityManager.flush();

        log.info(etudiantEniBDD.toString());

        DonneesPerso donneesPersoBDD = etudiantEniBDD.getDonneesPerso();
        Integer idDonneesPerso = donneesPersoBDD.getId();
        donneesPersoBDD.setEtudiantEni(null);

        donneesPersoRepository.delete(donneesPersoBDD);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("TEST01");
        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);

        assertThat(optionalEtudiantEni.isPresent()).isFalse();
        assertThat(optionalDonneesPerso.isPresent()).isFalse();
    }


}
