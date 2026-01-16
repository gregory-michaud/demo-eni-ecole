package fr.eni.demo.bll;

import fr.eni.demo.dal.UserRepository;
import fr.eni.demo.security.jwt.AuthenticationRequest;
import fr.eni.demo.security.jwt.AuthenticationResponse;
import fr.eni.demo.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private JwtService jwtService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        // appel de spring security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                authenticationRequest.getPseudo(),
                authenticationRequest.getPassword()));

        // appel du service de génération du token
        String jwt = jwtService.generateToken(authenticationRequest.getPseudo());

        return new AuthenticationResponse(jwt);
    }
}
