package cat.tecnocampus.apollofy.application;

import cat.tecnocampus.apollofy.application.exceptions.ElementNotFoundInBBDD;
import cat.tecnocampus.apollofy.domain.PlayList;
import cat.tecnocampus.apollofy.domain.Track;
import cat.tecnocampus.apollofy.domain.UserFy;
import cat.tecnocampus.apollofy.persistence.LikeTrackRepository;
import cat.tecnocampus.apollofy.persistence.PlayListRepository;
import cat.tecnocampus.apollofy.persistence.TrackRepository;
import cat.tecnocampus.apollofy.persistence.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserPlayListController {
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;
    private final PlayListRepository playListRepository;
    private final LikeTrackRepository likeTrackRepository;

    public UserPlayListController(TrackRepository trackRepository,
                                  UserRepository userRepository, PlayListRepository playListRepository,
                                  LikeTrackRepository likeTrackRepository) {
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.playListRepository = playListRepository;
        this.likeTrackRepository = likeTrackRepository;
    }


    public List<UserFy> getUsers() {
        return userRepository.findAll();
    }

    public List<PlayList> getPlayLists() {
        return playListRepository.findAll();
    }

    public PlayList getPlayListById(Long id) {
        return playListRepository.findById(id).orElseThrow(() -> new ElementNotFoundInBBDD("PlayList with id " + id));
    }

    @Transactional
    public void createPlayList(PlayList playList, String ownerEmail) {
        UserFy user = userRepository.findByEmail(ownerEmail).orElseThrow(() -> new ElementNotFoundInBBDD("User " + ownerEmail));
        playList.setOwner(user);

        playList.setTracks(getPlayListTracksFromDB(playList));
        playListRepository.save(playList);
    }

    private Set<Track> getPlayListTracksFromDB(PlayList playList) {
        return playList.getTracks().stream().map(t -> trackRepository.findById(t.getId()).orElseThrow(() -> new ElementNotFoundInBBDD("Track with id " + t.getId())))
                .collect(Collectors.toSet());
    }

    @Transactional
    public void addTracksToPlayList(Long playListId, List<Long> tracksId) {
        PlayList playListDB = playListRepository.findById(playListId).orElseThrow(() -> new RuntimeException("Play list doesn't exist"));
        tracksId.stream().map(tid -> trackRepository.findById(tid).orElseThrow(() -> new ElementNotFoundInBBDD("Track with id " + tid))).forEach(t -> playListDB.addTrack(t));
    }

    public UserFy getMe(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundInBBDD("User " + email));
    }

    public List<PlayList> getUserPlayLists(String email) {
        return playListRepository.findUserPlayLists(email);
    }

}
