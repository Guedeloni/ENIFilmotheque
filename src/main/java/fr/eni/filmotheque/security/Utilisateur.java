package fr.eni.filmotheque.security;

import fr.eni.filmotheque.bo.Membre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class Utilisateur implements UserDetails {
    private Membre membre;			// attribut qui va contenir un membre
    /**
     * getAuthorities() => doit renvoyer la liste des autorisations de notre Utilisateur
     * Si jamais notre membre a l'attribut "admin" à true => role "admin", sinon : role "user"
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (membre.isAdmin()) {
            // Légère différence entre authorities et rôles : autorisation "ROLE_admin" = rôle "admin"
            return List.of(new SimpleGrantedAuthority("ROLE_admin"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_user"));
        }
    }
    @Override
    public String getPassword() { return this.membre.getPassword(); }
    @Override
    public String getUsername() { return this.membre.getLogin(); }
    @Override
    public boolean isAccountNonExpired() { return true; }	// <= true tt. le tps, pas de gest. d'expirat. des cpts.
    @Override
    public boolean isAccountNonLocked() { return true; }	// <= true tt. le tps, pas de gest. de blocage des cpts.
    @Override
    public boolean isCredentialsNonExpired() { return true; }	// Gestion de l'expiration du MdP utilisateur
    @Override
    public boolean isEnabled() { return true; }		// Gestion des utilisateurs actifs ou non
}
