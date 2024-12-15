package org.codeus.patterns.case1.core.profiler;

/**
 * Simple implementation of the {@link Profiler} interface.
 * Performs time profiling using native Java functionality.
 * Elapsed time is returned when calling {@link SimpleTimeProfiler#endProfiling()}
 */
public class SimpleTimeProfiler implements Profiler {

  private long startTime;

  /**
   * Saves start point nanoseconds of profiling.
   */
  @Override
  public void startProfiling() {
    System.out.printf("%s starting profiling...%n", getClass().getName());
    startTime = System.nanoTime();
  }

  /**
   * Captures end point nanoseconds of profiling. Returns formatted diff between end and start points as elapsed time.
   */
  @Override
  public String endProfiling() {
    System.out.printf("%s ending profiling...%n", getClass().getName());
    long elapsed = System.nanoTime() - startTime;
    return "Elapsed: %d ns".formatted(elapsed);
  }
}
