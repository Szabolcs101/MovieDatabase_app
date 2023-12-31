package hu.inf.unideb.Repository;

import hu.inf.unideb.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByOnPlanned(boolean onPlanned);
    List<Movie> findByOnCompleted(boolean onCompleted);

}
