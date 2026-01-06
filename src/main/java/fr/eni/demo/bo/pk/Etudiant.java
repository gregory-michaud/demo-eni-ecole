package fr.eni.demo.bo.pk;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "STUDENT")
@IdClass(EtudiantPK.class)
public class Etudiant {

    @Id
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Id
    @Column(name = "STUDENT_REGISTRATION", nullable = false, unique = true, length = 100)
    private String immatriculation;

    @Column(name = "LAST_NAME", nullable = false, length = 90)
    private String nom;

    @Column(name = "FIRST_NAME", nullable = false, length = 150)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER", length = 12)
    private String numDom;

    @Column(name = "CELL_NUMBER", length = 12)
    private String numPortable;

    @Column(name = "PERSONAL_EMAIL", nullable = false, unique = true, length = 255)
    private String emailPersonnel;

}
