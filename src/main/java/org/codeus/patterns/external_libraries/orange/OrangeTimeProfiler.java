package org.codeus.patterns.external_libraries.orange;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple implementation of the {@link TimeProfiler} interface.
 * Performs time profiling using native Java functionality.
 * Elapsed time is returned when calling {@link OrangeTimeProfiler#endTimeProfiling()}
 */
public class OrangeTimeProfiler implements TimeProfiler {

  private long startTime;

  /**
   * Saves start point nanoseconds of profiling.
   */
  @Override
  public void startTimeProfiling() {
    System.out.printf("%s starting profiling...%n", getClass().getName());
    startTime = System.nanoTime();
  }

  /**
   * Saves end point nanoseconds of profiling. Returns diff between end and start points as elapsed time.
   */
  @Override
  public long endTimeProfiling() {
    System.out.printf("%s ending profiling...%n", getClass().getName());
    return System.nanoTime() - startTime;
  }

}
