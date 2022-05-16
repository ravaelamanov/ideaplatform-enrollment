package com.relamanov.ideaplatform.reader;

import com.relamanov.ideaplatform.domain.Ticket;

import java.util.Iterator;

public interface TicketsReader extends Iterator<Ticket>, AutoCloseable {
}
