package fr.eni.demo.bo.stagiaire;

import fr.eni.demo.bo.pk.Etudiant;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "STUDENT_CLASS")
public class Promo {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME",  nullable = false, unique = true,  length = 12)
    private String nom;

    @OneToMany(mappedBy = "promo", cascade = CascadeType.ALL, orphanRemoval = true, fetch =  FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<EtudiantEni> etudiants;
}
