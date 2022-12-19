package cat.tecnocampus.apollofy.persistence;

import cat.tecnocampus.apollofy.domain.Playlist;
import cat.tecnocampus.apollofy.domain.PlaylistTrack;
import cat.tecnocampus.apollofy.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/* TODO x.4
   Implement a JpaRepository named PlayListTrackRepository that provides the queries required by
   the UserPlayListController service.
 */
public interface PlayListTrackRepository extends JpaRepository<PlaylistTrack, Long> {
    Optional<PlaylistTrack> findByTrackAndPlaylist(Track track, Playlist playlist);

    List<PlaylistTrack> findByPlaylistId(Long id);
}
