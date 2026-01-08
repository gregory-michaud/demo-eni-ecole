package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @Test
    public void test_update(){

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

        employee.setNom("newNomTEST");
        Employe employeSave = employeRepository.save(employee);
        log.info(employeSave.toString());

        assertThat(employeSave.getNom()).isEqualTo("newNomTEST");


    }

    @Test
    public void test_delete(){

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

        int idEmploye = employee.getId();

        employeRepository.delete(employee);

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);

        assertThat(optionalEmploye.isPresent()).isFalse();
    }

    @Test
    public void test_findAll(){

        Employe employee = Employe
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("nTest@campus-eni.fr")
                .immatriculation("TEST123")
                .numDom("0245789865")
                .build();

        Employe employee2 = Employe
                .builder()
                .nom("nomTEST2")
                .prenom("prenomTEST2")
                .email("nTest2@campus-eni.fr")
                .immatriculation("TEST1232")
                .numDom("0245789862")
                .build();



        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.flush();

        log.info(employee.toString());
        log.info(employee2.toString());

        List<Employe> listeEmployes = employeRepository.findAll();

        assertThat(listeEmployes).isNotNull();
        assertThat(listeEmployes).isNotEmpty();
        assertThat(listeEmployes.size()).isEqualTo(2);

    }





}
