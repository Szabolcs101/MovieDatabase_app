package hu.inf.unideb.Controller;

import hu.inf.unideb.DTO.MovieDTO;
import hu.inf.unideb.DTO.SeriesDTO;
import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.Series;
import hu.inf.unideb.Service.Impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

}
