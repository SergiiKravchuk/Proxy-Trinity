package org.codeus.patterns.case1.core.profiler;

/**
 * A local interface, to which you have full access and can adjust if needed.
 */
public interface Profiler {

  /**
   * Starts profiling.
   */
  void startProfiling();

  /**
   * Ends profiling. Returns formatted result of the profiling.
   */
  String endProfiling();
}
