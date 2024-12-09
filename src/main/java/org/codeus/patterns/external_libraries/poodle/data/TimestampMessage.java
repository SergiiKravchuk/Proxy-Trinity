package org.codeus.patterns.external_libraries.poodle.data;

import java.time.LocalDateTime;

public record TimestampMessage(LocalDateTime localDateTime) implements Message {
}
