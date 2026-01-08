package fr.eni.demo.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@SuperBuilder

@Entity
@Table(name = "EMPLOYEE")
/*@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCR")
@DiscriminatorValue(value = "E")*/
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe {

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
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

    @OneToOne(cascade =  CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "CIVILITY_ID")
    private Civilite civilite;

}
