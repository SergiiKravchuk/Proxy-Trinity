package org.codeus.patterns.case3;

import org.codeus.patterns.case3.external.PerformanceOrientedService;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.case3.external.ValidationOrientedService;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.data.EventMessage;
import org.codeus.patterns.external_libraries.poodle.data.validation.Validator;

public class Case3Client {

  public void doHeavyLiftingPart1(ProcessingLogic processingLogic,
                                  TimeProfiler profiler,
                                  PerformanceOrientedService performanceOrientedService) {
    //TODO 3.1: The `processingLogic` call should be profiled using `TimeProfiler` during the `performanceOrientedService` call
    // but we CANNOT alter `PerformanceOrientedService`
    performanceOrientedService.doHeavyLifting(processingLogic);
  }

  public void doHeavyLiftingPart2(ProcessingLogic processingLogic,
                                  TimeProfiler profiler,
                                  Validator<EventMessage> validator,
                                  ValidationOrientedService<EventMessage> validationOrientedService) {
    //TODO 3.2: The `processingLogic` call should be profiled using `TimeProfiler` and
    // its input `EventMessage` should be validated using `EventMessageValidator`
    // during the `performanceOrientedService` but we CANNOT alter `ValidationOrientedService`
    // NOTE: the profiler logic should be called before the validation.
    validationOrientedService.doHeavyLifting(processingLogic);
  }
}
