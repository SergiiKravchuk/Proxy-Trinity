package org.codeus.patterns.case2.core;

import org.codeus.patterns.case2.external.ExternalService;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;

public class Case2Client {

  //TODO 2: Apply one design pattern to the module to profile `org.codeus.patterns.case2.core.LocalServiceImpl`
  // using (`OrangeTimeProfiler`) inside the `org.codeus.patterns.case2.external.ExternalService`
  // without changing source code of the `LocalServiceImpl` and `ExternalService`.

  public void doHeavyLifting(LocalService localService, TimeProfiler timeProfiler, ExternalService externalService) {

    //TODO 2.1: Calls of the `localService` should be profiled using the `TimeProfiler` during `externalService.process()`
    // but we CANNOT alter `ExternalService`
    externalService.process(localService);
  }
}
