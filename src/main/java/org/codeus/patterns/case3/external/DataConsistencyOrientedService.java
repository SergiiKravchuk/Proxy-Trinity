package org.codeus.patterns.case3.external;

import org.codeus.patterns.external_libraries.poodle.data.Message;
import org.codeus.patterns.external_libraries.poodle.data.producer.Producer;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 * <br><br>
 * Simple service that requires additional pre-filtering of {@link Producer} messages
 * and profiling of those messages processing.
 */
public class DataConsistencyOrientedService<T extends Message> {

  private final Producer<T> producer;

  public DataConsistencyOrientedService(Producer<T> producer) {
    this.producer = producer;
  }

  /**
   * Performs some logic that involves calls of the {@link ProcessingLogic}.
   */
  public void doHeavyLifting(ProcessingLogic processingLogic) {
    System.out.printf("%s starting processing...%n", getClass().getName());
    processingLogic.process(producer.produce());
    System.out.printf("%s ending processing...%n", getClass().getName());
  }


}
