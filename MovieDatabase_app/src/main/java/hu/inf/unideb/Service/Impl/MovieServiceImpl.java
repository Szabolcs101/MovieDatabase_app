package hu.inf.unideb.Service.Impl;

import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Repository.MovieRepository;
import hu.inf.unideb.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {this.movieRepository = movieRepository;}

    @Override
    public List<Movie> getAllMovies() {return movieRepository.findAll();}

    @Override
    public List<Movie> getPlannedMovies() {return movieRepository.findByOnPlanned(true);}

    @Override
    public Movie getMovieById(Integer id){

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    @Override
    public void addToPlanningList(Integer Id) {
        Optional<Movie> optionalMovie = movieRepository.findById(Id);
        optionalMovie.ifPresent(movie -> {
            movie.setOnPlanned(true);
            movieRepository.save(movie);
        });
    }

    @Override
    public void deleteFromPlanningList(Integer Id){
        Optional<Movie> optionalMovie = movieRepository.findById(Id);
        optionalMovie.ifPresent(movie -> {
            movie.setOnPlanned(false);
            movieRepository.delete(movie);
        });
    }

    @Override
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer id){
        movieRepository.deleteById(id);
    }
}
