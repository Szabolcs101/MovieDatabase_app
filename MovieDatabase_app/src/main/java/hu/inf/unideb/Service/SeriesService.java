package hu.inf.unideb.Service;

import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.Series;

import java.util.List;

public interface SeriesService {

    List<Series> getAllSeries();

    List<Series> getPlannedSeries(Long userId);

    List<Series> getCompletedSeries(Long userId);

    List<Series> getWatchedSeries(Long userId);

    Series getSeriesById(Integer id);

    void addToPlanningList(Long userId, Integer seriesId);

    void deleteFromPlanningList(Long userId, Integer seriesId);

    void addToCompletedList(Long userId, Integer seriesId);

    void deleteFromCompletedList(Long userId, Integer seriesId);

    void addToWatchedList(Long userId, Integer seriesId);

    void deleteFromWatchedList(Long userId, Integer seriesId);

    void increaseWatchedEpisodes(Integer id);

    Series createSeries(Series series);

    Series updateSeries(Series series);

    void deleteSeries(Integer id);
}
