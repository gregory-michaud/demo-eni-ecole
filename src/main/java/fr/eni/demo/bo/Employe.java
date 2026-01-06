package fr.eni.demo.bo;

import jakarta.persistence.*;
import lombok.*;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@Builder

@Entity
@Table(name = "EMPLOYEE")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @Column(name = "LAST_NAME",  nullable = false,  length = 90)
    private String nom;

    @Column(name = "FIRST_NAME",  nullable = false,  length = 150)
    private String prenom;

    @Column(nullable = false,  length = 255, unique = true)
    private String email;

    @Column(name = "EMPLOYEE_REGISTRATION",  nullable = false,  length = 100, unique = true)
    private String immatriculation;

    @Column(name = "HOME_PHONE_NUMBER",  length = 12)
    private String numDom;

    @Column(name = "CELL_NUMBER",  length = 12)
    private String numPortable;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

}
