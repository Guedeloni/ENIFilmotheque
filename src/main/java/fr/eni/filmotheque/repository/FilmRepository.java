package fr.eni.filmotheque.repository;

import fr.eni.filmotheque.bo.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    public List<Film> findByTitreContaining(String titre);

}
