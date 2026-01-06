package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestEmployeRepository {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private TestEntityManager entityManager;


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

        Employe employeSave = employeRepository.save(employee);

        log.info(employeSave.toString());

        assertThat(employeSave.getId()).isGreaterThan(0);
    }

    @Test
    public void test_findById(){

        Employe employee = Employe
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("nTest@campus-eni.fr")
                .immatriculation("TEST123")
                .numDom("0245789865")
                .build();

        entityManager.persist(employee);
        entityManager.flush();

        log.info(employee.toString());

        Optional<Employe> optionalEmploye = employeRepository.findById(employee.getId());

        assertThat(optionalEmploye.isPresent()).isTrue();

        Employe employeFind = optionalEmploye.get();
        log.info(employeFind.toString());
    }






}
