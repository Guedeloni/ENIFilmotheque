package fr.eni.filmotheque.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
@Entity
@Getter @Setter @ToString @NoArgsConstructor
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
                                        private long    id;
    @Digits(fraction = 0, integer = 1)
    @Min(1) @Max(5)                     private int     note;
                                        private String  commentaire;
    @ManyToOne                          private Membre  membre;
    @ManyToOne
    @JsonBackReference                  private Film    film;

    public Avis(long id, int note, String commentaire, Membre membre, Film film) {
        this.id             = id;
        this.note           = note;
        this.commentaire    = commentaire;
        this.membre         = membre;
        this.film           = film;
    }
}