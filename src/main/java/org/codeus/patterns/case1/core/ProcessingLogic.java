package org.codeus.patterns.case1.core;

import org.codeus.common.Result;
import org.codeus.patterns.case1.core.profiler.Profiler;

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


  public Result processWithProfiling(Profiler profiler) {
    System.out.printf("%s starting profiling using %s...%n", getClass().getName(), profiler.getClass().getName());
    profiler.startProfiling();
    Result result = this.process();
    String report = profiler.endProfiling();

    System.out.println(report);

    return result;
  }
}
