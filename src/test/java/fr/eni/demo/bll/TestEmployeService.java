package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestEmployeService {

    @Autowired
    private EmployeService employeService;

    @MockitoBean// Injection d'un Mock du EmployeDAO
    private EmployeDAO employeRepository;


    @Test
    void test01_ajouter_tousParametresValides() {
        int id = 1;

        Employe employe = Employe
                .builder()
                .id(id)
                .nom("BAILLE")
                .prenom("Anne-Lise")
                .email("abaille@campus-eni.fr")
                .immatriculation("ENI_Ecole_012892")
                .build();

        //Définir le comportement du Repository mocké
        when(employeRepository.read(id)).thenReturn(Optional.of(employe));

        //Comportemnet à valider
        employeService.ajouter(employe);

        // Vérification de l'ajout dans la liste des employés
        Optional<Employe> employeDB = employeRepository.read(id);
        assertTrue(employeDB.isPresent());
        assertThat(employe.getImmatriculation()).isEqualTo(employeDB.get().getImmatriculation());
        assertThat(employe.getEmail()).isEqualTo(employeDB.get().getEmail());
        assertThat(employe.getNom()).isEqualTo(employeDB.get().getNom());
        assertThat(employe.getPrenom()).isEqualTo(employeDB.get().getPrenom());
        assertThat(employe.getNumDom()).isEqualTo(employeDB.get().getNumDom());
        assertThat(employe.getNumPortable()).isEqualTo(employeDB.get().getNumPortable());
    }

    // Employe null
    @Test
    void test_ajouter_employe_null() {
        assertThrows(RuntimeException.class, () -> employeService.ajouter(null));
    }

    // Nom nul - la validation métier l'interdit - nom est obligatoire
    @Test
    void test_ajouter_sansNom() {
        Employe employe = Employe
                .builder()
                .id(2)
                .prenom("Stephane")
                .email("sgobin@campus-eni.fr")
                .immatriculation("ENI_Ecole_012111")
                .build();
        assertThrows(RuntimeException.class, () -> employeService.ajouter(employe));
    }

    // Immatriculation vide ou nulle - la validation métier l'interdit
    @Test
    void test_ajouter_immatriculationVide() {
        Employe employe = Employe
                .builder()
                .id(2)
                .nom("GOBIN")
                .prenom("Stephane")
                .email("sgobin@campus-eni.fr")
                .immatriculation("")
                .build();
        assertThrows(RuntimeException.class, () -> employeService.ajouter(employe));
    }

    // Immatriculation Unique - la validation métier l'interdit
    @Test
    void test_ajouter_immatriculationUnique() {
        Employe employe1 = Employe
                .builder()
                .id(1)
                .nom("BAILLE")
                .prenom("Anne-Lise")
                .email("abaille@campus-eni.fr")
                .immatriculation("ENI_Ecole_012892")
                .build();
        //employeService.ajouter(employe1);

        //Définir le comportement du Repository avec findByImmatriculation
        when(employeRepository.findByImmatriculation("ENI_Ecole_012892")).thenReturn(Optional.of(employe1));

        Employe employe2 = Employe
                .builder()
                .id(2)
                .nom("GOBIN")
                .prenom("Stephane")
                .email("sgobin@campus-eni.fr")
                .immatriculation("ENI_Ecole_012892")
                .build();
        assertThrows(RuntimeException.class, () -> employeService.ajouter(employe2));
    }

    @Test
    void test_chargerTousEmployes_isEmpty() {
        final List<Employe> lstEmployes = employeService.chargerTousEmployes();
        assertThat(lstEmployes).isEmpty();
    }

    @Test
    void test_chargerTousEmployes() {
        // Arrange
        List<Employe> employes = new ArrayList<>();
        employes.add(
                Employe
                        .builder()
                        .id(1)
                        .nom("BAILLE")
                        .prenom("Anne-Lise")
                        .email("abaille@campus-eni.fr")
                        .immatriculation("ENI_Ecole_012892")
                        .build());
        employes.add(
                Employe
                        .builder()
                        .id(2)
                        .nom("GOBIN")
                        .prenom("Stephane")
                        .email("sgobin@campus-eni.fr")
                        .immatriculation("ENI_Ecole_012111")
                        .build());
        when(employeRepository.findAll()).thenReturn(employes);

        // Act
        List<Employe> result = employeService.chargerTousEmployes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("ENI_Ecole_012892", result.get(0).getImmatriculation());
        assertEquals("ENI_Ecole_012111", result.get(1).getImmatriculation());
    }



}
