package fr.eni.demo.bo.formation;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"immatriculation"})
@ToString(exclude = {"coursDispenses"})
@Builder

@Entity
@Table(name = "TRAINER")
public class Formateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYE_ID")
    private Integer id;

    @Column(name = "LAST_NAME",  nullable = false,  length = 90)
    private String nom;

    @Column(name = "FIRST_NAME",   nullable = false,  length = 150)
    private String prenom;

    @Column(name = "EMAIL", nullable = false, unique = true,  length = 255)
    private String email;

    @Column(name = "EMPLOYE_REGISTRATION", nullable = false, unique = true,  length = 100)
    private String immatriculation;

    @Column(name = "HOME_PHONE_NUMBER", length = 12)
    private String numDom;

    @Column(name = "CELL_PHONE_NUMBER", length = 12)
    private String numPortable;

    @Column(name = "COMPUTER_SCIENCE_COURSE", length = 150)
    private String filiere;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COMPUTER_COURSES_PROVIDED",
               joinColumns=@JoinColumn(name = "EMPLOYE_ID"),
                inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private @Builder.Default List<Cours> coursDispenses = new ArrayList<>();







}
