package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeDAOImpl implements EmployeDAO {

    private List<Employe> employes = new ArrayList<>();

    @Override
    public void create(Employe employe) {
        employes.add(employe);
    }

    @Override
    public Optional<Employe> read(Integer id) {
        return employes
                .stream()
                .filter(item -> item.getId() == id)
                .findAny();
    }

    @Override
    public Optional<Employe> findByImmatriculation(String immatriculation) {
        return employes
                .stream()
                .filter(item -> item.getImmatriculation() == immatriculation)
                .findAny();
    }

    @Override
    public List<Employe> findAll() {
        return employes;
    }

    @Override
    public void update(Employe employe) {
        Optional<Employe> optionalEmploye = read(employe.getId());
        if (optionalEmploye.isPresent()) {
            Employe emp = optionalEmploye.get();
            emp.setEmail(employe.getEmail());
            emp.setPrenom(employe.getPrenom());
        }
    }

    //Il est important que les méthodes Equals et HashCode soient redéfinies pour cela
    @Override
    public void delete(Employe employe) {
        employes.remove(employe);
    }


}
