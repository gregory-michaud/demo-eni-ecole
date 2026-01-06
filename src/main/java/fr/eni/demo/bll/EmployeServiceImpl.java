package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Permet de faire injecter la couche DAL associée
@AllArgsConstructor
@Service
public class EmployeServiceImpl implements EmployeService {
    private EmployeRepository  employeRepository;

    @Override
    public void ajouter(Employe employe) {
        // Validation des données de l'employé avant sauvegarde
        if (employe == null) {
            throw new RuntimeException("L'employé n'est pas renseigné");
        }
        validerImmatriculation(employe);
        validerChaineNonNulle(employe.getNom(), "Vous devez renseigner le nom");
        validerChaineNonNulle(employe.getPrenom(), "Vous devez renseigner le prénom");
        validerChaineNonNulle(employe.getEmail(), "Vous devez renseigner un email");

        employeRepository.save(employe);
    }

    @Override
    public List<Employe> chargerTousEmployes() {

        return employeRepository.findAll();
    }

    private void validerChaineNonNulle(String chaine, String msgErreur) {
        if (chaine == null || chaine.isBlank())
            throw new RuntimeException(msgErreur);
    }

    private void validerImmatriculation(Employe employe) {
        // Valider que l'immatriculation n'est pas nule ou vide
        validerChaineNonNulle(employe.getImmatriculation(), "L'immatriculation n'a pas été renseignée");
        // TODO Immatriculation doit être unique

           // throw new RuntimeException("L'immatriculation doit être unique");

    }

}
