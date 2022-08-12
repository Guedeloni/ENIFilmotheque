package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.util.FilmException;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.Util;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class FilmServiceImplDev implements FilmService {
    private List<Film> filmList = new ArrayList<>();

    // Simulation d'enregistrement existant ds. le constructeur du service
    private FilmServiceImplDev() {
        Participant participant_01   = new Participant(1, "Sidney", "Lumet", false, true,
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.gTboKn_33chTWhfc467ykQHaK3%26pid%3DApi&f=1"
        );
        Participant participant_02   = new Participant(2, "Henry", "Fonda", true, false,
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.v5OJOsM1-5T4q_PvaWfkLQHaJ_%26pid%3DApi&f=1"
        );
        Participant participant_03   = new Participant(3, "Martin", "Balsam", true, false,
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.8qT7C7KCc7P-4ruVehM8BQHaIz%26pid%3DApi&f=1"
        );

        List<Participant> participantList_01 = new ArrayList<Participant>();
        participantList_01.add(participant_02);
        participantList_01.add(participant_03);

        Film film_01   = new Film(1, "12 HOMMES EN COLERE", LocalDate.of(1957, 04, 10), 96,
                            "Un juré réfractaire tente d'empêcher une erreur judiciaire en forçant les autres membres du jury à réexaminer les preuves.",
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.e3_wBa0iDUyT9epx6s8czQHaJ4%26pid%3DApi&f=1",
                            new Genre(2, "DRAME"), participant_01, participantList_01, null);
//        Film film_02   = new Film(2, "PULP FICTION", LocalDate.of(1994, 05, 21), 154,
//                            "Les vies de deux hommes de main, d'un boxeur, de la femme d'un gangster et de deux braqueurs s'entremêlent dans quatre histoires de violence et de rédemption.",
//                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.gMaz0thOVt6DuSxrmK_q3AHaKg%26pid%3DApi&f=1",
//                            new Genre(2, "DRAME"));
        filmList.add(film_01);
//        filmList.add(film_02);
    }

    @Override
    public List<Film> getFilmList() {return filmList;}

    @Override
    public Film getFilmById(long id) throws FilmException {
        Film film = new Film();
        film = null;
        for (Film f : filmList) {
            if (f.getId() == id) {
                film = f;
                break;
            }
        }
        if (film == null) throw new FilmException(Message.FILM_NOT_EXIST.showMsg());
        return film;
    }

    @Override
    public List<Film> getFilmListByTitre(String titre) {
        return null;
    }
    @Override
    public void addFilm(Film film) throws FilmException {
        long sizeOfList = filmList.size();
        String titreNormalise = Util.normalizeStringToCapitalize(film.getTitre());
        LocalDate dateSortie = film.getDateSortie();

        // Verification d'existance ou non
        for (Film f : filmList) {
            if (f.getTitre().equals(titreNormalise) & f.getDateSortie().equals(dateSortie)) {
                throw new FilmException(Message.FILM_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau film
        film.setId(sizeOfList+=1);
        film.setTitre(titreNormalise);
        filmList.add(film);
    }

}
