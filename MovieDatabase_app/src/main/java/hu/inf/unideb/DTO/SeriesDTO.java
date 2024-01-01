package hu.inf.unideb.DTO;

import hu.inf.unideb.Entity.Movie;
import hu.inf.unideb.Entity.Series;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SeriesDTO {

    private int id;

    private String title;

    private String description;

    private String genre;

    private String characters;

    private String studio;

    private String director;

    private String length;

    private String episodes;

    private boolean onPlanned = false;

    private boolean onWatching = false;

    private boolean onCompleted = false;

    public static SeriesDTO fromEntity(Series series){
        return SeriesDTO.builder()
                .id(series.getId())
                .title(series.getTitle())
                .description(series.getDescription())
                .genre(series.getGenre())
                .characters(series.getCharacters())
                .studio(series.getStudio())
                .director(series.getDirector())
                .length(series.getLength())
                .episodes(series.getEpisodes())
                .onPlanned(series.isOnPlanned())
                .onWatching(series.isOnWatching())
                .onCompleted(series.isOnCompleted())
                .build();
    }

    public static Series toEntity(SeriesDTO seriesDTO){
        return Series.builder()
                .id(seriesDTO.getId())
                .title(seriesDTO.getTitle())
                .description(seriesDTO.getDescription())
                .genre(seriesDTO.getGenre())
                .characters(seriesDTO.getCharacters())
                .studio(seriesDTO.getStudio())
                .director(seriesDTO.getDirector())
                .length(seriesDTO.getLength())
                .episodes(seriesDTO.getEpisodes())
                .onPlanned(seriesDTO.isOnPlanned())
                .onWatching(seriesDTO.isOnWatching())
                .onCompleted(seriesDTO.isOnCompleted())
                .build();
    }

}
