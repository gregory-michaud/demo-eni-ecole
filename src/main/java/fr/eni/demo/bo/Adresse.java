package fr.eni.demo.bo;


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
@Table(name = "ADDRESS")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer id;

    @Column(name = "STREET",  nullable = false,  length = 250)
    private String rue;

    @Column(name = "POSTAL_CODE",  nullable = false,  length = 5)
    private String codePostal;

    @Column(name = "CITY",  nullable = false,  length = 150)
    private String ville;

}
