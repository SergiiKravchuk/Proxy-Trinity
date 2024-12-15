package org.codeus.patterns.external_libraries.poodle.data.filter;

import org.codeus.patterns.external_libraries.poodle.data.Message;

/**
 * An External interface, consider it as an interface of another module of your project or an external library,
 * so you don't have direct access to its sources.
 * <p>This class SHOULD NOT BE altered in the course of this task.
 * <br>
 * <br>
 * Interface for filtering {@link Message} implementations.
 */
public interface Filter<T extends Message> {

  /**
   * Check if the given {@link Message} should be preserved according to a domain logic.
   * @param message a message to check
   * @return true if message should be preserved, false - otherwise.
   */
  boolean filter(T message);
}
