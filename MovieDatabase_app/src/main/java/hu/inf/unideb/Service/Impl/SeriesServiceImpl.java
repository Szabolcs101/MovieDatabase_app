package hu.inf.unideb.Service.Impl;

import hu.inf.unideb.Entity.Series;
import hu.inf.unideb.Repository.SeriesRepository;
import hu.inf.unideb.Service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository) {this.seriesRepository = seriesRepository;}

    @Override
    public List<Series> getAllSeries() {return seriesRepository.findAll();}

    @Override
    public List<Series> getPlannedSeries() {
        return seriesRepository.findByOnPlanned(true);
    }

    @Override
    public List<Series> getCompletedSeries() {
        return seriesRepository.findByOnCompleted(true);
    }

    @Override
    public Series getSeriesById(Integer id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        return optionalSeries.orElse(null);
    }

    @Override
    public void addToPlanningList(Integer id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        optionalSeries.ifPresent(series -> {
            series.setOnPlanned(true);
            seriesRepository.save(series);
        });
    }

    @Override
    public void deleteFromPlanningList(Integer id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        optionalSeries.ifPresent(series -> {
            series.setOnPlanned(false);
            seriesRepository.delete(series);
        });
    }

    @Override
    public void addToCompletedList(Integer id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        optionalSeries.ifPresent(series -> {
            series.setOnCompleted(true);
            seriesRepository.save(series);
        });
    }

    @Override
    public void deleteFromCompletedList(Integer id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        optionalSeries.ifPresent(series -> {
            series.setOnCompleted(false);
            seriesRepository.delete(series);
        });
    }

    @Override
    public void addToWatchingList(Integer id) {

    }

    @Override
    public void deleteFromWatchingList(Integer id) {

    }

    @Override
    public Series createSeries(Series series) {return seriesRepository.save(series);}

    @Override
    public Series updateSeries(Series series) {return seriesRepository.save(series);}

    @Override
    public void deleteSeries(Integer id) {seriesRepository.deleteById(id);}
}
