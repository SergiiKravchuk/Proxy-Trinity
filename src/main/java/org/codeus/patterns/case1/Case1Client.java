package org.codeus.patterns.case1;

import org.codeus.patterns.case1.core.ProcessingLogic;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.MemoryProfiler;

public class Case1Client {

  public void doHeavyLifting(ProcessingLogic processingLogic, MemoryProfiler memoryProfiler, TimeProfiler timeProfiler) {

    //TODO 1.1: Calls of the `processingLogic` should be profiled using provided Profiler (either TimeProfiler or MemoryProfiler)
    // Currently, this requirement is achieved by overloading the `processWithProfiling` method.
    // Refactor and simplify code so future updates can be done seamlessly.

    processingLogic.process();
    processingLogic.processWithProfiling(memoryProfiler);
    processingLogic.processWithProfiling(timeProfiler);
  }
}
