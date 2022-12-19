package cat.tecnocampus.apollofy.application.dto;

// Records are immutable data classes that require only the type and name of fields. Ideal for DTOs.
public record PlaylistTrackDTO(Long trackId, Long startTimeMillis, Long endTimeMillis) { }
