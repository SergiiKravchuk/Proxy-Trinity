package org.codeus.patterns.external_libraries.poodle.data.filter;

import org.codeus.patterns.external_libraries.poodle.data.EventMessage;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple implementation of the {@link Filter} interface.
 * Validates {@link EventMessage}s.
 */
public class EventMessageFilter implements Filter<EventMessage> {

  /**
   * Check if a given {@link EventMessage} should be filtered out by its {@link EventMessage#root()} content.
   * @param message - a message to check
   * @return  {@code true} if message.root() is not {@code null}, {@code false} - otherwise.
   */
  @Override
  public boolean filter(EventMessage message) {
    return message.root() != null;
  }
}
