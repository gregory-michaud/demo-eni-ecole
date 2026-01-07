package fr.eni.demo.bo.stagiaire;

import fr.eni.demo.bo.pk.Etudiant;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "STUDENT_DATA")
public class DonneesPerso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LAST_NAME",  nullable = false, length = 90)
    private String nom;

    @Column(name = "FIRST_NAME",  nullable = false, length = 150)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER",  nullable = true, length = 12)
    private String numDom;

    @Column(name = "CELL_NUMBER",  nullable = false, length = 12)
    private String numPortable;

    @Column(name = "PERSONAL_EMAIL",  nullable = false, unique = true, length = 255)
    private String emailPersonnel;

    @OneToOne(mappedBy = "donneesPerso", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EtudiantEni etudiantEni;


}
