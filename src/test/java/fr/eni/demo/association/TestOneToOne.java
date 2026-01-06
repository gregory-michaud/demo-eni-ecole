package fr.eni.demo.association;


import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestOneToOne {

    @Autowired
    private EmployeRepository employeRepository;

    @Test
    public void test_save(){

        Employe employee = Employe
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("nTest@campus-eni.fr")
                .immatriculation("TEST123")
                .numDom("0245789865")
                .build();

        Adresse adresse = Adresse
                .builder()
                .rue("TEST123")
                .codePostal("44000")
                .ville("Nantes")
                .build();

        employee.setAdresse(adresse);
        Employe employeSave = employeRepository.save(employee);

        log.info(employeSave.toString());

        assertThat(employeSave.getId()).isGreaterThan(0);

        assertThat(employeSave.getAdresse().getId()).isGreaterThan(0);


    }
}
