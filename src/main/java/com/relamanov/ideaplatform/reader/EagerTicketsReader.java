package com.relamanov.ideaplatform.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.relamanov.ideaplatform.domain.Ticket;
import com.relamanov.ideaplatform.exceptions.ResourceException;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class EagerTicketsReader extends AbstractTicketsReader {
    private final Reader fileReader;
    private Ticket[] tickets;
    private int currentIndex = 0;

    public EagerTicketsReader(String resourcePath) {
        Objects.requireNonNull(resourcePath);

        URI uri;

        try {
            URL resource = getClass().getClassLoader().getResource(resourcePath);
            if (resource == null) {
                throw new ResourceException(resourcePath);
            }

            uri = resource.toURI();
        } catch (URISyntaxException ex) {
            throw new ResourceException(resourcePath);
        }

        try {
            fileReader = new FileReader(new File(uri));
        } catch (FileNotFoundException fnf) {
            throw new ResourceException(fnf);
        }
    }

    @Override
    public boolean hasNext() {
        initTicketsIfNull();

        return currentIndex < tickets.length;
    }

    @Override
    public Ticket next() {
        initTicketsIfNull();

        return tickets[currentIndex++];
    }

    private void initTicketsIfNull() {
        if (tickets == null) {
            JsonArray ticketsArray = gson.fromJson(fileReader, JsonObject.class).get("tickets").getAsJsonArray();
            tickets = gson.fromJson(ticketsArray, Ticket[].class);
        }
    }


    @Override
    public void close() {
        try {
            fileReader.close();
        } catch (IOException ignored) {
            //ignored
        }
    }
}
