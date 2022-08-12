package fr.eni.filmotheque.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean                    // On définit un bean pour la configuration des routes
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**** Sur quels chemins impose-t-on d'être authentifié ****/
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")      // Si route /admin/***, rôle admin nécessaire
                .antMatchers("/prive/**").authenticated()       // Sinon, si route /prive/***, authentification nécessaire
                .anyRequest().permitAll().and()                 // Sinon, on autorise les autres requêtes
                .logout().logoutSuccessUrl("/").and()
                /**** On précise qu'on souhaite une authentification username/password ****/
                .formLogin();	//.loginPage("/login") si on veut avoir une page personalisée de login
        http.csrf().disable();  // <= Permet d'eviter les erreurs 403 sur les requetes POST d'API
                                // En principe, il faudrait gerer des token
        return http.build();
    }
    @Bean			// <= définition d'un bean pour l'utilitaire d'encryption de mot de passe
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}