package hu.inf.unideb.DTO;
import lombok.*;
import hu.inf.unideb.Entity.Movie;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieDTO {
    private int id;

    private String title;

    private String description;

    private String genre;

    private String characters;

    private String studio;

    private String director;

    private String length;

    private boolean onPlanned = false;

    private boolean onCompleted = false;

    public static MovieDTO fromEntity(Movie movie){
        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .characters(movie.getCharacters())
                .studio(movie.getStudio())
                .director(movie.getDirector())
                .length(movie.getLength())
                .onPlanned(movie.isOnPlanned())
                .onCompleted(movie.isOnCompleted())
                .build();
    }

    public static Movie toEntity(MovieDTO movieDTO){
        return Movie.builder()
                .id(movieDTO.getId())
                .title(movieDTO.getTitle())
                .description(movieDTO.getDescription())
                .genre(movieDTO.getGenre())
                .characters(movieDTO.getCharacters())
                .studio(movieDTO.getStudio())
                .director(movieDTO.getDirector())
                .length(movieDTO.getLength())
                .onPlanned(movieDTO.isOnPlanned())
                .onCompleted(movieDTO.isOnCompleted())
                .build();
    }
}
