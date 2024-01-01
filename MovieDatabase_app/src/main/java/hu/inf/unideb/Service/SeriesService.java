package hu.inf.unideb.Service;

import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.Series;

import java.util.List;

public interface SeriesService {

    List<Series> getAllSeries();

    List<Series> getPlannedSeries();

    List<Series> getCompletedSeries();

    Series getSeriesById(Integer id);

    void addToPlanningList(Integer id);

    void deleteFromPlanningList(Integer id);

    void addToCompletedList(Integer id);

    void deleteFromCompletedList(Integer id);

    void addToWatchingList(Integer id);

    void deleteFromWatchingList(Integer id);

    Series createSeries(Series series);

    Series updateSeries(Series series);

    void deleteSeries(Integer id);
}
