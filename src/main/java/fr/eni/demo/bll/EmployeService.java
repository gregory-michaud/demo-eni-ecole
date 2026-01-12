package fr.eni.demo.bll;

import java.util.List;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;

public interface EmployeService {
	void ajouter(Employe employe);

	List<Employe> chargerTousEmployes();
	
	Employe chargerUnEmployeParId(int id);
	
	//Pour valider les transactions
	void ajouterEmploye(Employe employe, Adresse adresse) ;
}
