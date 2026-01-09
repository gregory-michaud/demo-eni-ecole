package fr.eni.demo.association;


import fr.eni.demo.bo.Civilite;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.CiviliteRepository;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestManyToOne {

    @Autowired
    private CiviliteRepository civiliteRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Test
    public void test_save(){

        Civilite civilite = Civilite
                .builder()
                .clef("M")
                .libelle("Monsieur")
                .build();

        civiliteRepository.save(civilite);

        Employe employee = Employe
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("nTest@campus-eni.fr")
                .immatriculation("TEST123")
                .numDom("0245789865")
                .build();

        employee.setCivilite(civilite);

        Employe employeBDD = employeRepository.save(employee);

        log.info(employeBDD.toString());

        assertThat(employeBDD.getId()).isGreaterThan(0);
        assertThat(employeBDD.getCivilite()).isEqualTo(civilite);
    }

    @Test
    public void test_delete(){

        Civilite civilite = Civilite
                .builder()
                .clef("M")
                .libelle("Monsieur")
                .build();

        civiliteRepository.save(civilite);

        Employe employee = Employe
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("nTest@campus-eni.fr")
                .immatriculation("TEST123")
                .numDom("0245789865")
                .build();

        employee.setCivilite(civilite);

        Employe employeBDD = employeRepository.save(employee);

        log.info(employeBDD.toString());

        assertThat(employeBDD.getId()).isGreaterThan(0);
        assertThat(employeBDD.getCivilite()).isEqualTo(civilite);

        Integer idEmploye = employeBDD.getId();

        employeRepository.delete(employeBDD);

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);
        assertThat(optionalEmploye.isPresent()).isFalse();

        Optional<Civilite> optionalCivilite = civiliteRepository.findById("M");
        assertThat(optionalCivilite.isPresent()).isTrue();

    }





}
