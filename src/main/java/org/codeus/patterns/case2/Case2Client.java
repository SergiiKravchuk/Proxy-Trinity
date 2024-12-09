package org.codeus.patterns.case2;

import org.codeus.patterns.case2.core.LocalService;
import org.codeus.patterns.case2.external.ExternalService;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;

public class Case2Client {

  public void doHeavyLifting(LocalService localService, TimeProfiler timeProfiler, ExternalService externalService) {

    //TODO 2.1: Calls of the `localService` should be profiled using the `TimeProfiler` during `externalService.process()`
    // but we CANNOT alter `ExternalService`
    externalService.process(localService);
  }
}
