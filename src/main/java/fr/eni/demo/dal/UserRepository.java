package fr.eni.demo.dal;

import fr.eni.demo.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
