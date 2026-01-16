package fr.eni.demo.controller;

import fr.eni.demo.bll.AuthenticationService;
import fr.eni.demo.security.jwt.AuthenticationRequest;
import fr.eni.demo.security.jwt.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/eniecole/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        // appel du service d'authentification
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);


        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }



}
