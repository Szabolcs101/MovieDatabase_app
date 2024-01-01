package hu.inf.unideb.Repository;

import hu.inf.unideb.Entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {

    List<Series> findByOnPlanned(boolean onPlanned);
    List<Series> findByOnCompleted(boolean onCompleted);
    List<Series> findByOnWatching(boolean onWatching);
}
