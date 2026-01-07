package fr.eni.demo.association;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class TestOneToOneEager {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    private Employe employee;

    private Adresse adresse;


    @BeforeEach
    public void insertEmploye(){
        employee = Employe
                .builder()
                .nom("nomTEST")
                .prenom("prenomTEST")
                .email("nTest@campus-eni.fr")
                .immatriculation("TEST123")
                .numDom("0245789865")
                .build();

        adresse = Adresse
                .builder()
                .rue("TEST123")
                .codePostal("44000")
                .ville("Nantes")
                .build();

        employee.setAdresse(adresse);

        employeRepository.save(employee);
    }

    @Test
    public void test_find(){

        Optional<Employe> optionalEmploye =  employeRepository.findById(employee.getId());

        log.info(optionalEmploye.get().toString());
        assertThat(optionalEmploye.isPresent()).isTrue();
        assertThat(optionalEmploye.get()).isEqualTo(employee);
        assertThat(optionalEmploye.get().getAdresse()).isEqualTo(adresse);
    }






}
