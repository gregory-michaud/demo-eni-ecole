package fr.eni.demo.bll;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;

import java.util.List;

public interface EmployeService {
    void ajouter(Employe employe);

    void ajouter(Employe employe, Adresse adresse);

    List<Employe> chargerTousEmployes();

}
