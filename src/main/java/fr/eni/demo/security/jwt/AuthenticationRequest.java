package fr.eni.demo.security.jwt;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"pseudo"})
public class AuthenticationRequest {

    private String pseudo;

    private String password;


}
