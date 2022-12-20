package cat.tecnocampus.apollofy.api;

import cat.tecnocampus.apollofy.application.DJListController;
import cat.tecnocampus.apollofy.application.dto.DJListTrackDTO;
import cat.tecnocampus.apollofy.domain.DJListTrackFragment;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class DJListRestController {

    private DJListController djListController;

    public DJListRestController(DJListController djListController) {
        this.djListController = djListController;
    }

    /* TODO 5.0
    Implement RestController method that handles @PostMapping("/playlist/{id}/tracks") API calls to register new track
    associations with the playlist specified as parameter.

    The method receives: Principal principal, Long id, List<PlaylistTrackDTO> tracks, where:

    Each PlaylistTrackDTO contains the following attributes: Long trackId, Long startTimeMillis, Long endTimeMillis

    This method is in charge of receiving the information from the HTTP request and delegating it to a method of the
    UserPlayListController Service. The service method may be named userPlayListController.addTracksToPlayListWithTimeRange

    */

    @PostMapping("/djlist/{id}/tracks")
    public void addTracksToPlayListWithTimeRange(Principal principal, @PathVariable Long id, @RequestBody List<DJListTrackDTO> tracks) {
        djListController.addTracksToPlaylistWithTimeRange(principal.getName(), id, tracks);
    }

    /* TODO x.1
    Implement RestController method that handles @GetMapping("/playlist/{playlistId}/tracks") API calls.

    This API provides the functionality to get all the associations of tracks with a playlist specified as parameter.

    The method has to return List<PlaylistTrack> as expected in the JUnit test.

    This method delegates to userPlayListController.getTracksByPlaylistId(playlistId),
    which, in turn, calls the corresponding JpaRepository query to get the results.

     */
    @GetMapping("/djlist/{playlistId}/tracks")
    public List<DJListTrackFragment> getTracksToPlayListWithTimeRange(@PathVariable Long playlistId) {
        return djListController.getTracksByPlaylistId(playlistId);
    }
}
