package fr.eni.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/eniecole/employes/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/eniecole/employes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/eniecole/employes").hasRole("ADMIN")
                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                // désactivation du csrf pour une API
                .csrf(csrf -> csrf.disable())
        ;

        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select pseudo, password, 1 from users where pseudo = ?");
        users.setAuthoritiesByUsernameQuery("select pseudo, authority from users where pseudo = ?");
        return users;
    }

}
