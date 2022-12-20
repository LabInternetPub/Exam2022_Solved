package cat.tecnocampus.apollofy.application.dto;

/*
TODO 4.3 You can use this record as DTO in order to receive track fragments

// Records are immutable data classes that require only the type and name of fields. Ideal for DTOs.
// It is like a class where field's gets and sets are already implemented
// If you are not confortable with records you can implement a class based on these fields.

 */
public record DJListTrackDTO(Long trackId, Long startTimeMillis, Long endTimeMillis) { }
