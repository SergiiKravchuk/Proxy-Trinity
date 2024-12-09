package org.codeus.patterns.external_libraries.poodle.data.validation;

import org.codeus.patterns.external_libraries.poodle.data.Message;

/**
 * An External interface, consider it as an interface of another module of your project or an external library,
 * so you don't have direct access to its sources.
 * This class SHOULD NOT BE altered in the course of this task.
 * <br>
 * <br>
 * Interface for content validation of {@link Message} implementations.
 */
public interface Validator<T extends Message> {

  /**
   * Validates given {@link Message} content according to domain logic.
   * @param message - a message to validate
   * @return true if message is valid, false - otherwise.
   */
  boolean validate(T message);
}
