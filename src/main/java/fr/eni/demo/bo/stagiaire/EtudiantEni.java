package fr.eni.demo.bo.stagiaire;

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
@Table(name = "ENI_STUDENT")
public class EtudiantEni {

    @Id
    @Column(name = "STUDENT_REGISTRATION",  nullable = false, unique = true, length = 100)
    private String immatriculation;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DATA_ID")
    private DonneesPerso donneesPerso;
}
