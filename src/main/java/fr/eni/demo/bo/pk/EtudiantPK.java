package fr.eni.demo.bo.pk;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EtudiantPK implements Serializable {

    @Serial
    private static final long serialVersionUID = -3846711155409764223L;

    private String email;

    private String immatriculation;
}
