package hu.inf.unideb.Service;
import hu.inf.unideb.Entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    List<Movie> getPlannedMovies();

    Movie getMovieById(Integer id);

    void addToPlanningList(Integer id);

    void deleteFromPlanningList(Integer id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Integer id);

}
