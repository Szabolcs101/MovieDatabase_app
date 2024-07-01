package hu.inf.unideb.Service;
import hu.inf.unideb.Entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    List<Movie> getPlannedMovies(Long userId);

    List<Movie> getCompletedMovies(Long userId);

    Movie getMovieById(Integer id);

    void addToPlanningList(Long userId, Integer movieId);

    void deleteFromPlanningList(Long userId, Integer movieId);

    void addToCompletedList(Long userId, Integer movieId);

    void deleteFromCompletedList(Long userId, Integer movieId);

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Integer id);

}
