package fr.eni.demo.bo;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@Slf4j
public class TestPatternLombok {

    @Test
    void test_patternBuilder_TousLesAttributs() {

        Employe annelise = Employe
                .builder()
                .id(1)
                .nom("BAILLE")
                .prenom("Anne-Lise")
                .email("abaille@campus-eni.fr")
                .immatriculation("ENI_Ecole_012892")
                .numDom("0299XXXXXX")
                .numPortable("0699XXXXXX")
                .build();


        log.info("test_patternBuilder_TousLesAttributs : " + annelise.toString());
        assertThat(annelise.getNom()).isEqualTo("BAILLE");
        assertThat(annelise.getImmatriculation()).isEqualTo("ENI_Ecole_012892");
        assertThat(annelise.getNumDom()).isEqualTo("0299XXXXXX");
        assertThat(annelise.getNumPortable()).isEqualTo("0699XXXXXX");
    }

    @Test
    void test_patternBuilder_QuelquesAttributs() {
        Employe stephane = Employe
                .builder()
                .id(2)
                .nom("GOBIN")
                .prenom("Stéphane")
                .email("sgobin@campus-eni.fr")
                .immatriculation("ENI_Ecole_011112")
                .numDom("0288XXXXXX")
                .build();
        log.info("test_patternBuilder_QuelquesAttributs : " + stephane.toString());
        assertThat(stephane.getNom()).isEqualTo("GOBIN");
        assertThat(stephane.getImmatriculation()).isEqualTo("ENI_Ecole_011112");
        assertThat(stephane.getNumDom()).isEqualTo("0288XXXXXX");
        assertNull(stephane.getNumPortable());
    }



}
