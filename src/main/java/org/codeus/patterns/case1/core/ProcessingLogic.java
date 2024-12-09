package org.codeus.patterns.case1.core;

import org.codeus.common.Result;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.MemoryProfiler;

import java.util.stream.Collectors;

public class ProcessingLogic {

  public Result process() {
    System.out.printf("%s starting processing...%n", getClass().getName());
    try {
      Thread.sleep(100L);
      return Result.SUCCESS;
    } catch (Exception e) {
      System.out.printf("Exception occurred during %s processing:\n %s%n", getClass().getName(), e.getMessage());
    }

    return Result.FAILURE;
  }


  public Result processWithProfiling(MemoryProfiler memoryProfiler) {
    System.out.printf("%s starting profiling using %s...%n", getClass().getName(), memoryProfiler.getClass().getName());
    memoryProfiler.startMemoryProfiling();
    Result result = this.process();
    memoryProfiler.endMemoryProfiling();

    String report = memoryProfiler.getResults().stream().collect(Collectors.joining("\n", "Memory Profiling Report:\n", ""));
    System.out.println(report);

    return result;
  }

  public Result processWithProfiling(TimeProfiler timeProfiler) {
    System.out.printf("%s starting profiling using %s...%n", getClass().getName(), timeProfiler.getClass().getName());
    timeProfiler.startTimeProfiling();
    Result result = this.process();
    long timeElapsed = timeProfiler.endTimeProfiling();

    System.out.printf("Time Elapsed:%d (nanoseconds)%n", timeElapsed);

    return result;
  }


}
