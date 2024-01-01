package hu.inf.unideb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "SERIES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Series {

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

    @Column(name = "EPISODES")
    private String episodes;

    @Column(name = "onPlanned")
    private boolean onPlanned = false;

    @Column(name = "onWatching")
    private boolean onWatching = false;

    @Column(name = "onCompleted")
    private boolean onCompleted = false;

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", characters='" + characters + '\'' +
                ", studio='" + studio + '\'' +
                ", director='" + director + '\'' +
                ", length='" + length + '\'' +
                ", episodes='" + episodes + '\'' +
                ", onPlanned=" + onPlanned +
                ", onWatching=" + onWatching +
                ", onCompleted=" + onCompleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return id == series.id && onPlanned == series.onPlanned && onWatching == series.onWatching && onCompleted == series.onCompleted && Objects.equals(title, series.title) && Objects.equals(description, series.description) && Objects.equals(genre, series.genre) && Objects.equals(characters, series.characters) && Objects.equals(studio, series.studio) && Objects.equals(director, series.director) && Objects.equals(length, series.length) && Objects.equals(episodes, series.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, genre, characters, studio, director, length, episodes, onPlanned, onWatching, onCompleted);
    }
}
