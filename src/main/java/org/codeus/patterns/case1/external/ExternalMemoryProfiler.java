package org.codeus.patterns.case1.external;

import java.util.ArrayList;
import java.util.List;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources.
 * <p>This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple implementation of the {@link MemoryProfiler} interface.
 * Performs profiling using native Java functionality.
 * Results can be aggregated in one Profiler instance and provided as a list of metrics.
 */
public class ExternalMemoryProfiler implements MemoryProfiler {

  private double startMemoryFootprint;
  private final List<String> results;

  public ExternalMemoryProfiler() {
    results = new ArrayList<>();
  }

  /**
   * Captures memory footprint as a starting point.
   */
  @Override
  public void startMemoryProfiling() {
    System.out.printf("%s starting profiling...%n", getClass().getName());
    startMemoryFootprint = getCurrentMemoryFootPrint();
  }

  /**
   * Captures memory footprint as an ending point. Saves result of the captures.
   */
  @Override
  public void endMemoryProfiling() {
    System.out.printf("%s ending profiling...%n", getClass().getName());
    double endMemoryFootprint = getCurrentMemoryFootPrint();
    results.add("Started with %s KB, ended %s KB (diff %s)".formatted(startMemoryFootprint, endMemoryFootprint, endMemoryFootprint - startMemoryFootprint));
  }

  /**
   * Returns aggregated metrics.
   */
  @Override
  public List<String> getResults() {
    return results;
  }

  private double getCurrentMemoryFootPrint() {
    return (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
  }
}
