package org.codeus.patterns.external_libraries.poodle.data;

public record EventMessage(String root, String value) implements Message {
}
