package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;

import java.util.List;
import java.util.Optional;

public interface EmployeDAO {
    void create(Employe employe);

    Optional<Employe> read(Integer id);

    Optional<Employe> findByImmatriculation(String immatriculation);

    List<Employe> findAll();

    void update(Employe employe);

    void delete(Employe employe);

}
