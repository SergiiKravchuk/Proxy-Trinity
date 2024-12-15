package org.codeus.patterns.case3.core;

import org.codeus.patterns.case3.external.PerformanceOrientedService;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.case3.external.DataConsistencyOrientedService;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.data.EventMessage;
import org.codeus.patterns.external_libraries.poodle.data.filter.Filter;

public class Case3Client {

  //TODO 3: Apply one design pattern to the module to apply different tools:
  // - profiling - org.codeus.patterns.external_libraries.orange.TimeProfiler;
  // - filtering - org.codeus.patterns.external_libraries.poodle.data.filter.EventMessageFilter.
  // These tools should be applied your org.codeus.patterns.case3.core.LocalServiceImpl in runtime
  // without changing source code of the LocalServiceImpl and any of external services/libraries.

  public void doHeavyLiftingPart1(ProcessingLogic processingLogic,
                                  TimeProfiler profiler,
                                  PerformanceOrientedService performanceOrientedService) {
    //TODO 3.1: The `processingLogic` call should be profiled using `TimeProfiler` during the `performanceOrientedService` call
    performanceOrientedService.doHeavyLifting(processingLogic);
  }

  public void doHeavyLiftingPart2(ProcessingLogic processingLogic,
                                  TimeProfiler profiler,
                                  Filter<EventMessage> filter,
                                  DataConsistencyOrientedService<EventMessage> dataConsistencyOrientedService) {
    //TODO 3.2: The `processingLogic` call should be profiled using `TimeProfiler` and
    // its input `EventMessage` should be filtered using `Filter<EventMessage>`
    // during the `performanceOrientedService`.
    // NOTE: the profiler logic should be called before the validation.
    dataConsistencyOrientedService.doHeavyLifting(processingLogic);
  }
}
