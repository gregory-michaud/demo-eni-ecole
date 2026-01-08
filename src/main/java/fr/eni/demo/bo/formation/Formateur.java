package fr.eni.demo.bo.formation;

import fr.eni.demo.bo.Employe;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder

@Entity
//@DiscriminatorValue(value = "F")
@Table(name = "TRAINER")
public class Formateur extends Employe  {



    @Column(name = "COMPUTER_SCIENCE_COURSE", length = 150)
    private String filiere;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COMPUTER_COURSES_PROVIDED",
               joinColumns=@JoinColumn(name = "EMPLOYE_ID"),
                inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    @ToString.Exclude
    private @Builder.Default List<Cours> coursDispenses = new ArrayList<>();







}
