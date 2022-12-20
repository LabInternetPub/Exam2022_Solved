package cat.tecnocampus.apollofy.persistence;

import cat.tecnocampus.apollofy.domain.DJList;
import cat.tecnocampus.apollofy.domain.Playlist;
import cat.tecnocampus.apollofy.domain.DJListTrackFragment;
import cat.tecnocampus.apollofy.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/* TODO x.4
   Implement a JpaRepository named PlayListTrackRepository that provides the queries required by
   the UserPlayListController service.
 */
public interface DJListTrackFragmentRepository extends JpaRepository<DJListTrackFragment, Long> {
    Optional<DJListTrackFragment> findByTrackAndDjList(Track track, DJList djList);

    List<DJListTrackFragment> findByDjListId(Long id);
}
