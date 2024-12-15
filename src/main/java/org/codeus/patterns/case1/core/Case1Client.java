package org.codeus.patterns.case1.core;

import org.codeus.patterns.case1.core.profiler.Profiler;
import org.codeus.patterns.case1.external.MemoryProfiler;

public class Case1Client {

  //TODO 1: Apply one design pattern to the module to support other (external) profilers without changing source code,
  // e.g. `org.codeus.patterns.case1.external.ExternalMemoryProfiler`.

  public void doHeavyLifting(ProcessingLogic processingLogic, Profiler profiler, MemoryProfiler memoryProfiler) {

    processingLogic.process();
    processingLogic.processWithProfiling(profiler);
//    processingLogic.processWithProfiling(memoryProfiler);
  }
}
