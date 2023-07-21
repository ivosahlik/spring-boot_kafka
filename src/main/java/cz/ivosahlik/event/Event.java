package cz.ivosahlik.event;

import java.sql.Timestamp;
import java.util.UUID;

public record Event<T>(
        UUID eventId,
        String name,
        Timestamp creationDate,

        T payload
) {}
