package fr.eni.filmotheque.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@Entity
@Getter @Setter @ToString @NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
                long id;
    @NotBlank   String nom;
    @NotBlank   String prenom;
                boolean acteur;
                boolean realisateur;
    @URL        String image;

    public Participant(long id, String nom, String prenom, boolean acteur, boolean realisateur, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.acteur = acteur;
        this.realisateur = realisateur;
        this.image = image;
    }
}
