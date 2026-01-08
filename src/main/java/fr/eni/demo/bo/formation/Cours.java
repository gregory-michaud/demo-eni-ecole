package fr.eni.demo.bo.formation;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "COMPUTER_COURSE")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPUTER_COURSE_ID")
    private Integer id;

    @Column(name = "COMPUTER_SCIENCE_COURSE", length = 150)
    private String filiere;

    @Column(name = "REFERENCE", length = 15)
    private String reference;

    @Column(name = "TITLE", length = 250)
    private String titre;

    @Column(name = "DURATION")
    private int duree;




}
