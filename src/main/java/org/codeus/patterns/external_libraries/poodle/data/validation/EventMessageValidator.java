package org.codeus.patterns.external_libraries.poodle.data.validation;

import org.codeus.patterns.external_libraries.poodle.data.EventMessage;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple implementation of the {@link Validator} interface.
 * Validates {@link EventMessage}s.
 */
public class EventMessageValidator implements Validator<EventMessage> {

  /**
   * Validates given {@link EventMessage#root()} data.
   * @param message - a message to validate
   * @return  {@code true} if message is not {@code null}, {@code false} - otherwise.
   */
  @Override
  public boolean validate(EventMessage message) {
    return message.root() != null;
  }
}
