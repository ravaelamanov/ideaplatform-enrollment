package com.relamanov.ideaplatform;

import com.relamanov.ideaplatform.domain.Ticket;
import com.relamanov.ideaplatform.reader.StreamTicketsReader;
import com.relamanov.ideaplatform.reader.TicketsReader;
import com.relamanov.ideaplatform.stats.Statistics;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) throws Exception {
        try (TicketsReader reader = new StreamTicketsReader("tickets.json")) {
            Stream<Ticket> ticketStream = ticketStream(reader).filter(Main::isFromVVOtoTLV);

            Statistics statistics = new Statistics(ticketStream.collect(Collectors.toList()));
            statistics.calculate();

            System.out.printf("Average flight duration: %f\n", statistics.getAverageFlightDuration());
            System.out.printf("90th percentile of flight duration: %f", statistics.getPercentile90FlightDuration());
        }
    }

    private static Stream<Ticket>  ticketStream(Iterator<Ticket> iterator) {
        Spliterator<Ticket> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }

    static boolean isFromVVOtoTLV(Ticket ticket) {
        return ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV");
    }
}
