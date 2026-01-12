package fr.eni.demo.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.AdresseRepository;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

//Permet de faire injecter la couche DAL associée
@AllArgsConstructor
@Service
public class EmployeServiceImpl implements EmployeService {
	private EmployeRepository employeRepository;
	private AdresseRepository adresseRepository;

	@Override
	public List<Employe> chargerTousEmployes() {
		return employeRepository.findAll();
	}

	@Override
	public Employe chargerUnEmployeParId(int id) {
		// Validation de l'identifiant
		if (id <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Employe> opt = employeRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucun employé correspond");
	}

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

	private void validerChaineNonNulle(String chaine, String msgErreur) {
		if (chaine == null || chaine.isBlank())
			throw new RuntimeException(msgErreur);
	}

	private void validerImmatriculation(Employe employe) {
		// Valider que l'immatriculation n'est pas nule ou vide
		validerChaineNonNulle(employe.getImmatriculation(), "L'immatriculation n'a pas été renseignée");
		// Immatriculation doit être unique
		// Appel de la méthode de requête spécifique : findByImmatriculation
		Employe employeDB = employeRepository.findByImmatriculation(employe.getImmatriculation());
		if (employeDB != null) {
			throw new RuntimeException("L'immatriculation doit être unique");
		}
	}

	@Override
	@Transactional
	public void ajouterEmploye(Employe employe, Adresse adresse) {
		// Validation des données de l'employé avant sauvegarde
		if (employe == null) {
			throw new RuntimeException("L'employé n'est pas renseigné");
		}
		validerImmatriculation(employe);
		validerChaineNonNulle(employe.getNom(), "Vous devez renseigner le nom");
		validerChaineNonNulle(employe.getPrenom(), "Vous devez renseigner le prénom");
		validerChaineNonNulle(employe.getEmail(), "Vous devez renseigner un email");

		employeRepository.save(employe);

		// Validation des données de l'adresse avant sauvegarde
		if (adresse == null) {
			throw new RuntimeException("L'adresse n'est pas renseignée");
		}
		adresseRepository.save(adresse);
	}

}
