package org.codeus.patterns.case2.core;

import org.codeus.common.Result;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;

public class Proxy implements LocalService {

  private final LocalService localService;
  private final TimeProfiler timeProfiler;

  public Proxy(LocalService localService, TimeProfiler timeProfiler) {
    this.localService = localService;
    this.timeProfiler = timeProfiler;
  }

  @Override
  public Result process() {
    timeProfiler.startTimeProfiling();
    Result result = localService.process();
    long timeElapsed = timeProfiler.endTimeProfiling();
    System.out.printf("Time Elapsed:%d (nanoseconds)%n", timeElapsed);

    return result;
  }
}
