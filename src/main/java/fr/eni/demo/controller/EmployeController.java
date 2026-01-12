package fr.eni.demo.controller;

import fr.eni.demo.bll.EmployeService;
import fr.eni.demo.bo.Employe;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/eniecole/employes")
public class EmployeController {

    private EmployeService employeService;

    @GetMapping
    public ResponseEntity<?> rechercherTousLesEmployes(){
        List<Employe> listeEmploye = employeService.chargerTousEmployes();

        if(listeEmploye == null || listeEmploye.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listeEmploye);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> rechercherEmployeParId(@PathVariable("id") String idEmploye){

        try{
            int id = Integer.parseInt(idEmploye);
            Employe employe = employeService.chargerUnEmployeParId(id);
            return ResponseEntity.ok(employe);
        } catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
        } catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
