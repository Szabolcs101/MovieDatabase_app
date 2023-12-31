package hu.inf.unideb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "MOVIE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "CHARACTERS")
    private String characters;

    @Column(name = "STUDIO")
    private String studio;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "LENGTH")
    private String length;

    @Column(name = "onPlanned")
    private boolean onPlanned = false;

    @Column(name = "onCompleted")
    private boolean onCompleted = false;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", characters='" + characters + '\'' +
                ", studio='" + studio + '\'' +
                ", director='" + director + '\'' +
                ", length='" + length + '\'' +
                ", isOnPlanned=" + onPlanned +
                ", isOnCompleted=" + onCompleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && onPlanned == movie.onPlanned && onCompleted == movie.onCompleted && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genre, movie.genre) && Objects.equals(characters, movie.characters) && Objects.equals(studio, movie.studio) && Objects.equals(director, movie.director) && Objects.equals(length, movie.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, genre, characters, studio, director, length, onPlanned, onCompleted);
    }
}
