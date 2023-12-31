package hu.inf.unideb.Service;
import hu.inf.unideb.Entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    List<Movie> getPlannedMovies();

    List<Movie> getCompletedMovies();

    Movie getMovieById(Integer id);

    void addToPlanningList(Integer id);

    void deleteFromPlanningList(Integer id);

    void addToCompletedList(Integer id);

    void deleteFromCompletedList(Integer id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Integer id);

}
