package cat.tecnocampus.apollofy.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class PlaylistTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long startTimeMillis;

    private Long endTimeMillis;

    @ManyToOne(optional = false)
    @NotNull
    private Track track;

    @ManyToOne(optional = false)
    @NotNull
    private Playlist playlist;

    public PlaylistTrack() {
    }

    public PlaylistTrack(Long startTimeMillis, Long endTimeMillis, Track track, Playlist playlist) {
        this.startTimeMillis = startTimeMillis;
        this.endTimeMillis = endTimeMillis;
        this.track = track;
        this.playlist = playlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(Long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public Long getEndTimeMillis() {
        return endTimeMillis;
    }

    public void setEndTimeMillis(Long endTimeMillis) {
        this.endTimeMillis = endTimeMillis;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistTrack that = (PlaylistTrack) o;
        return Objects.equals(id, that.id) && Objects.equals(startTimeMillis, that.startTimeMillis) && Objects.equals(endTimeMillis, that.endTimeMillis) && Objects.equals(track, that.track) && Objects.equals(playlist, that.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTimeMillis, endTimeMillis, track, playlist);
    }

    @Override
    public String toString() {
        return "PlaylistTrack{" +
                "id=" + id +
                ", startTimeMillis=" + startTimeMillis +
                ", endTimeMillis=" + endTimeMillis +
                ", track=" + track +
                ", playlist=" + playlist +
                '}';
    }
}
