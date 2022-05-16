package com.relamanov.ideaplatform.stats;

import com.relamanov.ideaplatform.domain.Ticket;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.util.List;
import java.util.stream.DoubleStream;

public class Statistics {
    private final List<Ticket> tickets;
    private double averageFlightDuration;
    private double percentile90FlightDuration;

    public Statistics(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void calculate() {
        double[] flightDurations = tickets.stream()
                .map(Ticket::getFlightDuration)
                .mapToDouble(Long::doubleValue)
                .toArray();

        averageFlightDuration = DoubleStream.of(flightDurations).average().orElse(0.0);

        percentile90FlightDuration = new Percentile().evaluate(flightDurations, 90);
    }

    public double getAverageFlightDuration() {
        return averageFlightDuration;
    }

    public double getPercentile90FlightDuration() {
        return percentile90FlightDuration;
    }
}
