package fr.eni.demo.association;


import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
import fr.eni.demo.dal.EtudiantEniRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestOneToOneBidirectionnel {

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

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

}
