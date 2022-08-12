package fr.eni.filmotheque.service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.util.GenreException;
import fr.eni.filmotheque.util.Message;
import fr.eni.filmotheque.util.Util;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class GenreServiceImplDev implements GenreService {

    private List<Genre> genreList = new ArrayList<>();

    // Simulation d'enregistrement existant ds. le constructeur du service
    private GenreServiceImplDev() {
        Genre comedie   = new Genre(1, "COMEDIE");
        Genre drame     = new Genre(2, "DRAME");
        genreList.add(comedie);
        genreList.add(drame);
    }

    @Override
    public List<Genre> getGenreList() {
        return genreList;
    }

    @Override
    public Genre getGenreById(long id) throws GenreException {
        Genre genre = new Genre();
        genre = null;
        for (Genre g : genreList) {
            if (g.getId() == id) {
                genre = g;
                break;
            }
        }
        if (genre == null) throw new GenreException(Message.GENRE_NOT_EXIST.showMsg());
        return genre;
    }

//    @Override
//    public Genre getGenreByLibelle(String libelle) throws GenreException {
//        Genre genre = new Genre();
//        for (Genre g : genreList) {
//            if (g.getLibelle() == libelle) {
//                genre = g;
//                break;
//            }
//            else throw new GenreException(Message.GENRE_NOT_EXIST.showMsg());
//        }
//        return genre;
//    }

    @Override
    public void updateGenre(Genre genre) throws GenreException {
    }

    @Override
    public void addGenre(Genre genre) throws GenreException {
        long sizeOfList = genreList.size();
        String libelleNormalise = Util.normalizeStringToUpperCase(genre.getLibelle());

        // Verification d'existance ou non
        for (Genre g : genreList) {
            if (g.getLibelle().equals(libelleNormalise)) {
                throw new GenreException(Message.GENRE_EXIST.showMsg());
            }
        }
        // Preparation des infos avant ajout du nouveau genre
        genre.setId(sizeOfList+=1);
        genre.setLibelle(libelleNormalise);
        genreList.add(genre);
    }
}
