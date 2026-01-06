package fr.eni.demo.bo;

import lombok.*;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@Builder
public class Employe {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String immatriculation;
    private String numDom;
    private String numPortable;

}
