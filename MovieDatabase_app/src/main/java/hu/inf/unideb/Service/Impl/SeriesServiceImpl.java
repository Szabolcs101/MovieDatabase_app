package hu.inf.unideb.Service.Impl;

import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Entity.Series;
import hu.inf.unideb.Repository.SeriesRepository;
import hu.inf.unideb.Repository.UserRepository;
import hu.inf.unideb.Service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final UserRepository userRepository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository, UserRepository userRepository) {
        this.seriesRepository = seriesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Series> getAllSeries() {return seriesRepository.findAll();}

    @Override
    public List<Series> getPlannedSeries(Long userId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getPlannedSeries();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Series> getWatchedSeries(Long userId){
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getWatchedSeries();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Series> getCompletedSeries(Long userId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return optionalUser.get().getCompletedSeries();
        }
        return Collections.emptyList();
    }

    @Override
    public Series getSeriesById(Integer id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        return optionalSeries.orElse(null);
    }

    @Override
    public void addToPlanningList(Long userId, Integer seriesId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        if(optionalSeries.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Series series = optionalSeries.get();

            if (!user.getPlannedSeries().contains(series)) {
                user.getPlannedSeries().add(series);
                series.setOnPlanned(true);
                userRepository.save(user);
                seriesRepository.save(series);
            }
        }
    }

    @Override
    public void deleteFromPlanningList(Long userId, Integer seriesId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        if(optionalSeries.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Series series = optionalSeries.get();

            user.getPlannedSeries().remove(series);

            series.setOnPlanned(false);
            userRepository.save(user);
            seriesRepository.save(series);
            }
        }

    @Override
    public void addToCompletedList(Long userId, Integer seriesId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        if(optionalSeries.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Series series = optionalSeries.get();

            if (!user.getCompletedSeries().contains(series)) {
                user.getCompletedSeries().add(series);
                series.setOnCompleted(true);
                userRepository.save(user);
                seriesRepository.save(series);
            }
        }
    }

    @Override
    public void deleteFromCompletedList(Long userId, Integer seriesId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        if(optionalSeries.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Series series = optionalSeries.get();

            user.getCompletedSeries().remove(series);

            series.setOnCompleted(false);
            userRepository.save(user);
            seriesRepository.save(series);
        }
    }

    @Override
    public void addToWatchedList(Long userId, Integer seriesId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        if(optionalSeries.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Series series = optionalSeries.get();

            if (!user.getWatchedSeries().contains(series)) {
                user.getWatchedSeries().add(series);
                series.setOnWatched(true);
                userRepository.save(user);
                seriesRepository.save(series);
            }
        }
    }

    @Override
    public void deleteFromWatchedList(Long userId, Integer seriesId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        if(optionalSeries.isPresent() && optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            Series series = optionalSeries.get();

            user.getWatchedSeries().remove(series);

            series.setOnWatched(false);
            userRepository.save(user);
            seriesRepository.save(series);
        }
    }

    @Override
    public void increaseWatchedEpisodes(Integer id) {
        Series series = seriesRepository.findById(id).orElse(null);

        if (series != null && series.getWatchedEpisodes() < series.getEpisodes()) {
            series.setWatchedEpisodes(series.getWatchedEpisodes() + 1);
            seriesRepository.save(series);
        }
    }

    @Override
    public Series createSeries(Series series) {return seriesRepository.save(series);}

    @Override
    public Series updateSeries(Series series) {return seriesRepository.save(series);}

    @Override
    public void deleteSeries(Integer id) {seriesRepository.deleteById(id);}
}
