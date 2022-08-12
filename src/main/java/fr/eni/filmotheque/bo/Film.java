package fr.eni.filmotheque.bo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter @ToString
//@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    private long                id;
    @NotBlank       private String              titre;
    @Past           private LocalDate           dateSortie;
    @Min(1)         private int                 duree;
    @NotBlank       private String              synopsis;
    @NotBlank @URL  private String              image;
    @ManyToOne      private Genre               genre;
    @ManyToOne      private Participant         realisateur;

    @ManyToMany
    @JoinTable(name="FilmParticipant",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "participant_id") })
                    private List<Participant>   acteurs;
    @OneToMany
    @JoinColumn(name = "film_id")
    @JsonManagedReference
                    private List<Avis>          avisList;

    public Film() { avisList = new ArrayList<>(); }

    public Film(long id, String titre, LocalDate dateSortie, int duree, String synopsis, String image, Genre genre,
                Participant realisateur, List<Participant> acteurs, List<Avis> avisList) {
        this.id             = id;
        this.titre          = titre;
        this.dateSortie     = dateSortie;
        this.duree          = duree;
        this.synopsis       = synopsis;
        this.image          = image;
        this.genre          = genre;
        this.realisateur    = realisateur;
        this.acteurs        = acteurs;
        this.avisList       = avisList;
    }
}
