package hu.inf.unideb.Controller;

import hu.inf.unideb.DTO.SeriesDTO;
import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Entity.Series;
import hu.inf.unideb.Repository.UserRepository;
import hu.inf.unideb.Service.Impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PrinterInfo;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class SeriesController {

    private final SeriesServiceImpl seriesServiceImpl;
    private final UserRepository userRepository;

    @Autowired
    public SeriesController(SeriesServiceImpl seriesServiceImpl, UserRepository userRepository) {
        this.seriesServiceImpl = seriesServiceImpl;
        this.userRepository = userRepository;
    }


    @PostMapping("/newSeries")
    public String createSeries(@ModelAttribute("series") SeriesDTO seriesDTO) {
        Series newSeries = SeriesDTO.toEntity(seriesDTO);
        newSeries.setOnPlanned(false);
        seriesServiceImpl.createSeries(newSeries);
        return "newSeries";
    }

    @GetMapping("newSeries")
    public String createSeriesForm(Model model) {
        model.addAttribute("series", new SeriesDTO());
        return "newSeries";
    }

    @GetMapping("/seriesPage")
    public String showSeriesPage(Model model){
        List<Series> allSeries = seriesServiceImpl.getAllSeries();
        if (allSeries != null) {
            for (Series series : allSeries) {
                series.setOnPlanned(false);
            }
        }
        model.addAttribute("allSeries", allSeries);
        return "seriesPage";
    }

    @GetMapping("/series/{seriesId}")
    public String showSeries(@PathVariable Integer seriesId, Model model){
        Series series = seriesServiceImpl.getSeriesById(seriesId);
        model.addAttribute( "series",series);
        return "series";
    }

    @GetMapping("/plannedSeriesPage")
    public String showPlannedSeriesPage(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<Series> plannedSeries = seriesServiceImpl.getPlannedSeries(userId);
        model.addAttribute("allSeries", plannedSeries);
        return "plannedSeriesPage";
    }

    @PostMapping("/addToPlannedSeries/{seriesId}")
    public String addToPlanningList(@PathVariable Integer seriesId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        seriesServiceImpl.addToPlanningList(userId, seriesId);
        return "redirect:/seriesPage";
    }

    //Should use @DeleteMapping but it's not working
    @GetMapping("/deleteFromPlannedSeries/{seriesId}")
    public String deleteFromPlanning(@PathVariable Integer seriesId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        seriesServiceImpl.deleteFromPlanningList(userId, seriesId);
        return "redirect:/plannedSeriesPage";
    }

    @GetMapping("/completedSeriesPage")
    public String showCompletedPage(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<Series> completedSeries = seriesServiceImpl.getCompletedSeries(userId);
        model.addAttribute("allSeries", completedSeries);
        return "completedSeriesPage";
    }

    @PostMapping("/addToCompletedSeries/{seriesId}")
    public String addToCompletedList(@PathVariable Integer seriesId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        seriesServiceImpl.addToCompletedList(userId, seriesId);
        return "redirect:/seriesPage";
    }

    @GetMapping("/deleteFromCompletedSeries/{seriesId}")
    public String deleteFromCompleted(@PathVariable Integer seriesId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        seriesServiceImpl.deleteFromCompletedList(userId, seriesId);
        return "redirect:/completedSeriesPage";
    }

    @GetMapping("/watchedSeriesPage")
    public String showWatchedPage(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<Series> watchedSeries = seriesServiceImpl.getWatchedSeries(userId);
        model.addAttribute("allSeries", watchedSeries);
        return "watchedSeriesPage";
    }

    @PostMapping("/addToWatchedSeries/{seriesId}")
    public String addToWatchedList(@PathVariable Integer seriesId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        seriesServiceImpl.addToWatchedList(userId, seriesId);
        return "redirect:/seriesPage";
    }

    @GetMapping("/deleteFromWatchedSeries/{seriesId}")
    public String deleteFromWatched(@PathVariable Integer seriesId, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        seriesServiceImpl.deleteFromWatchedList(userId, seriesId);
        return "redirect:/watchedSeriesPage";
    }

    @PostMapping("/increaseWatchedEpisodes")
    public String increaseWatchedEpisodesForm(@RequestParam("seriesId") Integer seriesId) {
        seriesServiceImpl.increaseWatchedEpisodes(seriesId);
        return "redirect:/watchedSeriesPage";
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String username = principal.getName();

        Optional<MyUser> user = userRepository.findByUsername(username);
        return user.map(MyUser::getId).orElse(null);
    }

}
