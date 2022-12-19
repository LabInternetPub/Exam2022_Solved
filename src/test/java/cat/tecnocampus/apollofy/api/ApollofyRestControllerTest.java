package cat.tecnocampus.apollofy.api;

import cat.tecnocampus.apollofy.application.UserPlayListController;
import cat.tecnocampus.apollofy.application.dto.PlaylistTrackDTO;
import cat.tecnocampus.apollofy.domain.PlaylistTrack;
import cat.tecnocampus.apollofy.domain.UserFy;
import cat.tecnocampus.apollofy.persistence.PlayListTrackRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ApollofyRestControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserPlayListController userPlayListController;

    @Autowired
    private PlayListTrackRepository playListTrackRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    void findAllTracks() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/tracks")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Sound of silence"));
        assertTrue(result.getResponse().getContentAsString().contains("Vuela con el viento"));
        assertTrue(result.getResponse().getContentAsString().contains("La lluna la pruna"));
    }

    @Test
    @WithMockUser("jalvarez@tecnocampus.cat")
    void findUser() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/me")).andExpect(status().isOk()).andReturn();
        UserFy userFy = objectMapper.readValue(result.getResponse().getContentAsString(), UserFy.class);
        assertEquals(userFy.getEmail(), "jalvarez@tecnocampus.cat");
        assertEquals(userFy.getName(), "Josep");
        assertEquals(userFy.getSecondName(), "Alvarez");
    }

    @Test
    // Simulate that we are executing the test being the user "mperez@tecnocampus.cat". It's a nice feature. Isn't it? :)
    @WithMockUser("mperez@tecnocampus.cat")
    void addTracksToPlayListWithTimeRange() throws Exception {

        // Create PlayTrackDTO to add two tracks to a playlist with their time range
        final PlaylistTrackDTO track1 = new PlaylistTrackDTO(1L, 3000L, 4000L);
        final PlaylistTrackDTO track2 = new PlaylistTrackDTO(2L, 2302L, 6789L);

        List<PlaylistTrackDTO> playlistTrackDTOList = Arrays.asList(new PlaylistTrackDTO[]{track1, track2});

        // TODO: PlaylistTrack start must be lower than end.

        // Convert DTO to JSON.
        String body = objectMapper.writeValueAsString(playlistTrackDTOList);

        mockMvc.perform(post("/api/playlist/1/tracks")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isCreated()) It should be 201 Created as Spotify official API doc, but we use 200
                .andExpect(status().isOk())
                .andReturn();

        // Get PlaylistTracks to ensure that the tracks have been added correctly in the previous POST API call:
        // post("/api/playlist/1/tracks")
        MvcResult result = mockMvc.perform(get("/api/playlist/1/tracks")).andExpect(status().isOk()).andReturn();

        // Convert HTTP response body from JSON to Java
        List<PlaylistTrack> playlistTracks = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        /*
        Check that the API get("/api/playlist/1/tracks") returns the same "playlistTrack" that we have associated with the playlist
        by means of the current JUnit test. To implement the test, the information of the DTO that we have passed in the body of the
        POST request post("/api/playlist/1/tracks") is compared with the information returned by the API: get("/api/playlist/1/tracks")

        For simplicity, it has not been specified as a requirement that the Playlist tracks must be in order.
        That is why the verification code is a little more complex.

         */
        assertTrue(playlistTracks.stream().anyMatch(playlistTrack -> playlistTrackEqualsDTO(playlistTrack, track1)));
        assertTrue(playlistTracks.stream().anyMatch(playlistTrack -> playlistTrackEqualsDTO(playlistTrack, track2)));
    }

    private static boolean playlistTrackEqualsDTO(PlaylistTrack playlistTrack, PlaylistTrackDTO trackToCheck) {
        return playlistTrack.getTrack().getId().equals(trackToCheck.trackId()) &&
                playlistTrack.getStartTimeMillis().equals(trackToCheck.startTimeMillis()) &&
                playlistTrack.getEndTimeMillis().equals(trackToCheck.endTimeMillis());
    }

}
