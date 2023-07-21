package cz.ivosahlik.dto;

import java.util.UUID;

public record Customer(
        UUID customerId,
        String name,
        String address
) {
}
