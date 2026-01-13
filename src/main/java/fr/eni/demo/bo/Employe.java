package fr.eni.demo.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "{employee.ln.blank-error}")
    @Size(max = 90)
    private String nom;

    @Column(name = "FIRST_NAME",  nullable = false,  length = 150)
    @NotBlank
    @Size(max = 150)
    private String prenom;

    @Column(nullable = false,  length = 255, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "EMPLOYEE_REGISTRATION",  nullable = false,  length = 100, unique = true)
    @NotBlank
    @Size(max = 100)
    private String immatriculation;

    @Column(name = "HOME_PHONE_NUMBER",  length = 12)
    @Size(max = 12)
    private String numDom;

    @Column(name = "CELL_NUMBER",  length = 12)
    @Size(max = 12)
    private String numPortable;

    @OneToOne(cascade =  CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    @NotNull
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "CIVILITY_ID")
    @NotNull(message = "{employee.civility.error}")
    private Civilite civilite;

}
