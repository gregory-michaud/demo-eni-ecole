package fr.eni.demo.dal;

import fr.eni.demo.bo.pk.Etudiant;
import fr.eni.demo.bo.pk.EtudiantPK;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@DataJpaTest
public class TestEtudiantRepository {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Test
    public void test_save(){

        Etudiant etudiant = Etudiant
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("emailTEST.campus-eni.fr")
                .immatriculation("immatriculationTEST")
                .numDom("0278451232")
                .emailPersonnel("emailPersonnelTEST@gmail.com")
                .build();

        Etudiant etudiantBDD = etudiantRepository.save(etudiant);

        EtudiantPK etudiantPK = EtudiantPK
                .builder()
                .email("emailTEST.campus-eni.fr")
                .immatriculation("immatriculationTEST")
                .build();

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantPK);

        assertTrue(optionalEtudiant.isPresent());

        log.info(optionalEtudiant.get().toString());

    }





}
