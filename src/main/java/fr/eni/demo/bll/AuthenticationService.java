package fr.eni.demo.bll;

import fr.eni.demo.security.jwt.AuthenticationRequest;
import fr.eni.demo.security.jwt.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
