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

    /* TODO 4.4
        Implement RestController method that handles @PostMapping("/playlist/{id}/tracks") API calls to register new track
        associations with the playlist specified as parameter. You will need to get the user somehow (maybe from the security).
        You want to receive a list of DJListTrackDTO in the request's body
    */

    @PostMapping("/djlist/{id}/tracks")
    public void addTracksToPlayListWithTimeRange(Principal principal, @PathVariable Long id, @RequestBody List<DJListTrackDTO> tracks) {
        djListController.addTracksToPlaylistWithTimeRange(principal.getName(), id, tracks);
    }
}
