package hu.inf.unideb.Service.Impl;

import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Repository.MovieRepository;
import hu.inf.unideb.Repository.UserRepository;
import hu.inf.unideb.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Movie> getAllMovies() {return movieRepository.findAll();}

    @Override
    public List<Movie> getPlannedMovies(Long userId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getPlannedMovies();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Movie> getCompletedMovies(Long userId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return optionalUser.get().getCompletedMovies();
        }
        return Collections.emptyList();
    }

    @Override
    public Movie getMovieById(Integer id){
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    @Override
    public void addToPlanningList(Long userId, Integer movieId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if(optionalMovie.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Movie movie = optionalMovie.get();

            if (!user.getPlannedMovies().contains(movie)) {
                user.getPlannedMovies().add(movie);
                movie.setOnPlanned(true);
                userRepository.save(user);
                movieRepository.save(movie);
            }
        }
    }

    @Override
    public void deleteFromPlanningList(Long userId, Integer movieId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            MyUser user = optionalUser.get();
            Movie movie = optionalMovie.get();

            user.getPlannedMovies().remove(movie);

            movie.setOnPlanned(false);
            userRepository.save(user);
            movieRepository.save(movie);
        }
    }

    @Override
    public void addToCompletedList(Long userId, Integer movieId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            MyUser user = optionalUser.get();
            Movie movie = optionalMovie.get();

            user.getPlannedMovies().remove(movie);
            user.getCompletedMovies().add(movie);

            movie.setOnPlanned(false);
            movie.setOnCompleted(true);

            userRepository.save(user);
            movieRepository.save(movie);
        }
    }

    @Override
    public void deleteFromCompletedList(Long userId, Integer movieId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            MyUser user = optionalUser.get();
            Movie movie = optionalMovie.get();

            user.getCompletedMovies().remove(movie);
            movie.setOnCompleted(false);

            movieRepository.save(movie);
            userRepository.save(user);
        }
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
