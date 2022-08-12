package fr.eni.filmotheque.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter @Setter @ToString @NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    id;
    @NotBlank
    private String  libelle;

    public Genre(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }


}
