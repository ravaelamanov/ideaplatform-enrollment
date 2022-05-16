package com.relamanov.ideaplatform.reader;

import com.google.gson.stream.JsonReader;
import com.relamanov.ideaplatform.domain.Ticket;
import com.relamanov.ideaplatform.exceptions.ResourceException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamTicketsReader extends AbstractTicketsReader {
    private final JsonReader reader;

    public StreamTicketsReader(String resourcePath) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new ResourceException(resourcePath);
        }
        reader = new JsonReader(new InputStreamReader(inputStream));

        try {
            reader.beginObject();
            reader.nextName();
            reader.beginArray();
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    @Override
    public void close() {
        try {
            reader.endArray();
            reader.endObject();
            reader.close();
        } catch (IOException ignored) {
            //ignored
        }
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.hasNext();
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    @Override
    public Ticket next() {
        return gson.fromJson(reader, Ticket.class);
    }
}
