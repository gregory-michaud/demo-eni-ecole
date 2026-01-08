package fr.eni.demo.bo;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "CIVILITY")
public class Civilite {

    @Id
    @Column(name = "CIVILITY_ID", length = 3, unique = true)
    private String clef;

    @Column(name = "LABEL",  length = 50, unique = true)
    private String libelle;

}
