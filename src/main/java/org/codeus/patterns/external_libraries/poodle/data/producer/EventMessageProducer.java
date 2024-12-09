package org.codeus.patterns.external_libraries.poodle.data.producer;


import org.codeus.patterns.external_libraries.poodle.data.EventMessage;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple implementation of the {@link Producer} interface.
 * Produces static instance of the {@link EventMessage}.
 */
public class EventMessageProducer implements Producer<EventMessage> {
  @Override
  public EventMessage produce() {
    return new EventMessage("events", "Hi");
  }
}
