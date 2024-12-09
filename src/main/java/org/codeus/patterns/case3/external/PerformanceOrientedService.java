package org.codeus.patterns.case3.external;

import org.codeus.patterns.external_libraries.poodle.data.TimestampMessage;

import java.time.LocalDateTime;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple service that requires additional profiling of produced messages processing.
 */
public class PerformanceOrientedService {

  /**
   * Performs some logic that involves calls of the {@link ProcessingLogic}.
   */
  public void doHeavyLifting(ProcessingLogic processingLogic) {
    System.out.printf("%s starting processing...%n", getClass().getName());
    processingLogic.process(new TimestampMessage(LocalDateTime.now()));
    System.out.printf("%s ending processing...%n", getClass().getName());
  }

}
