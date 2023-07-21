package cz.ivosahlik.event;

import java.sql.Timestamp;
import java.util.UUID;

public record MyEvent<T>(
        UUID eventId,
        T payload,
        Timestamp creationDate
) {}
