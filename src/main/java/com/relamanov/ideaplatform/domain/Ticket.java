package com.relamanov.ideaplatform.domain;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String origin;
    @SerializedName("origin_name")
    private String originName;
    private String destination;
    @SerializedName("destination_name")
    private String destinationName;
    @SerializedName("departure_date")
    private LocalDate departureDate;
    @SerializedName("departure_time")
    private LocalTime departureTime;
    @SerializedName("arrival_date")
    private LocalDate arrivalDate;
    @SerializedName("arrival_time")
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private BigDecimal price;

    public long getFlightDuration() {
        return ChronoUnit.SECONDS.between(
                LocalDateTime.of(departureDate, departureTime),
                LocalDateTime.of(arrivalDate, arrivalTime)
        );
    }
}
