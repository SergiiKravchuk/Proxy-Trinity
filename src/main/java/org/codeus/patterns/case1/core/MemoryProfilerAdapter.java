package org.codeus.patterns.case1.core;

import org.codeus.patterns.case1.core.profiler.Profiler;
import org.codeus.patterns.case1.external.MemoryProfiler;

import java.util.stream.Collectors;

public class MemoryProfilerAdapter implements Profiler {

  private final MemoryProfiler memoryProfiler;

  public MemoryProfilerAdapter(MemoryProfiler memoryProfiler) {
    this.memoryProfiler = memoryProfiler;
  }

  @Override
  public void startProfiling() {
    memoryProfiler.startMemoryProfiling();

  }

  @Override
  public String endProfiling() {
    memoryProfiler.endMemoryProfiling();

    return memoryProfiler.getResults().stream().collect(Collectors.joining("\n", "Memory Profiling Report:\n", ""));
  }
}
