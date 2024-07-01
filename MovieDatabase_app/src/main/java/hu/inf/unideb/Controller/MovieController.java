package hu.inf.unideb.Controller;

import hu.inf.unideb.DTO.MovieDTO;
import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Repository.UserRepository;
import hu.inf.unideb.Service.Impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    private final MovieServiceImpl movieServiceImpl;
    private final UserRepository userRepository;

    @Autowired
    public MovieController(MovieServiceImpl movieServiceImpl, UserRepository userRepository) {
        this.movieServiceImpl = movieServiceImpl;
        this.userRepository = userRepository;
    }

    @PostMapping("/newMovie")
    public String createMovie(@ModelAttribute("movie") MovieDTO movieDTO) {
        Movie newMovie = MovieDTO.toEntity(movieDTO);
        newMovie.setOnPlanned(false);
        movieServiceImpl.createMovie(newMovie);
        return "newMovie";
    }

    @GetMapping("newMovie")
    public String createMovieForm(Model model) {
        model.addAttribute("movie", new MovieDTO());
        return "newMovie";
    }

    @GetMapping("/moviePage")
    public String showMoviePage(Model model){
        List<Movie> movies = movieServiceImpl.getAllMovies();
        if (movies != null) {
            for (Movie movie : movies) {
                movie.setOnPlanned(false);
            }
        }
        model.addAttribute("movies", movies);
        return "moviePage";
    }

    @GetMapping("/movie/{movieId}")
    public String showMovie(@PathVariable Integer movieId, Model model){
        Movie movie = movieServiceImpl.getMovieById(movieId);
        model.addAttribute( "movie",movie);
        return "movie";
    }

    @GetMapping("/plannedPage")
    public String showPlannedPage(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<Movie> plannedMovies = movieServiceImpl.getPlannedMovies(userId);
        model.addAttribute("movies", plannedMovies);
        return "plannedPage";
    }

    @PostMapping("/addToPlanned/{movieId}")
    public String addToPlanningList(@PathVariable Integer movieId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        movieServiceImpl.addToPlanningList(userId, movieId);
        return "redirect:/moviePage";
    }

    //Should use @DeleteMapping but it's not working
    @GetMapping("/deleteFromPlanned/{movieId}")
    public String deleteFromPlanning(@PathVariable Integer movieId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        movieServiceImpl.deleteFromPlanningList(userId, movieId);
        return "redirect:/plannedPage";
    }

    @GetMapping("/completedPage")
    public String showCompletedPage(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<Movie> completedMovies = movieServiceImpl.getCompletedMovies(userId);
        model.addAttribute("movies", completedMovies);
        return "completedPage";
    }

    @PostMapping("/addToCompleted/{movieId}")
    public String addToCompletedList(@PathVariable Integer movieId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        movieServiceImpl.addToCompletedList(userId, movieId);
        return "redirect:/moviePage";
    }

    @GetMapping("/deleteFromCompleted/{movieId}")
    public String deleteFromCompleted(@PathVariable Integer movieId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        movieServiceImpl.deleteFromCompletedList(userId, movieId);
        return "redirect:/completedPage";
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String username = principal.getName();

        Optional<MyUser> user = userRepository.findByUsername(username);
        return user.map(MyUser::getId).orElse(null);
    }

}
