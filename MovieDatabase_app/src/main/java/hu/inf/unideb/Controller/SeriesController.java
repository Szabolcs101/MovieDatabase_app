package hu.inf.unideb.Controller;

import hu.inf.unideb.DTO.SeriesDTO;
import hu.inf.unideb.Entity.Series;
import hu.inf.unideb.Service.Impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SeriesController {

    private final SeriesServiceImpl seriesServiceImpl;

    @Autowired
    public SeriesController(SeriesServiceImpl seriesServiceImpl) {this.seriesServiceImpl = seriesServiceImpl;}


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
    public String showPlannedSeriesPage(Model model) {
        List<Series> plannedSeries = seriesServiceImpl.getPlannedSeries();
        model.addAttribute("allSeries", plannedSeries);
        return "plannedSeriesPage";
    }

    @PostMapping("/addToPlannedSeries/{seriesId}")
    public String addToPlanningList(@PathVariable Integer seriesId) {
        seriesServiceImpl.addToPlanningList(seriesId);
        return "redirect:/seriesPage";
    }

    //Should use @DeleteMapping but it's not working
    @GetMapping("/deleteFromPlannedSeries/{seriesId}")
    public String deleteFromPlanning(@PathVariable Integer seriesId) {
        seriesServiceImpl.deleteFromPlanningList(seriesId);
        return "redirect:/plannedSeriesPage";
    }

    @GetMapping("/completedSeriesPage")
    public String showCompletedPage(Model model) {
        List<Series> completedSeries = seriesServiceImpl.getCompletedSeries();
        model.addAttribute("allSeries", completedSeries);
        return "completedSeriesPage";
    }

    @PostMapping("/addToCompletedSeries/{seriesId}")
    public String addToCompletedList(@PathVariable Integer seriesId) {
        seriesServiceImpl.addToCompletedList(seriesId);
        return "redirect:/seriesPage";
    }

    @GetMapping("/deleteFromCompletedSeries/{seriesId}")
    public String deleteFromCompleted(@PathVariable Integer seriesId) {
        seriesServiceImpl.deleteFromCompletedList(seriesId);
        return "redirect:/completedSeriesPage";
    }

    @GetMapping("/watchedSeriesPage")
    public String showWatchedPage(Model model) {
        List<Series> watchedSeries = seriesServiceImpl.getWatchedSeries();
        model.addAttribute("allSeries", watchedSeries);
        return "watchedSeriesPage";
    }

    @PostMapping("/addToWatchedSeries/{seriesId}")
    public String addToWatchedList(@PathVariable Integer seriesId) {
        seriesServiceImpl.addToWatchedList(seriesId);
        return "redirect:/seriesPage";
    }

    @GetMapping("/deleteFromWatchedSeries/{seriesId}")
    public String deleteFromWatched(@PathVariable Integer seriesId) {
        seriesServiceImpl.deleteFromWatchedList(seriesId);
        return "redirect:/watchedSeriesPage";
    }

    @PostMapping("/increaseWatchedEpisodes")
    public String increaseWatchedEpisodesForm(@RequestParam("seriesId") Integer seriesId) {
        seriesServiceImpl.increaseWatchedEpisodes(seriesId);
        return "redirect:/watchedSeriesPage";
    }

}
