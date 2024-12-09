package org.codeus.patterns.external_libraries.orange;

/**
 * An External interface, consider it as an interface of another module of your project or an external library,
 * so you don't have direct access to its sources.
 * This class SHOULD NOT BE altered in the course of this task.
 */
public interface TimeProfiler {

  void startTimeProfiling();
  long endTimeProfiling();

}
