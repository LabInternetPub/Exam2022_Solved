package cat.tecnocampus.apollofy.application.dto;

// Records are immutable data classes that require only the type and name of fields. Ideal for DTOs.
// It is like a class where field's gets and sets are implemented
public record DJListTrackDTO(Long trackId, Long startTimeMillis, Long endTimeMillis) { }
