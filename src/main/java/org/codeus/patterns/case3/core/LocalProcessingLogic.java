package org.codeus.patterns.case3.core;

import org.codeus.common.Result;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.external_libraries.poodle.data.Message;

public class LocalProcessingLogic implements ProcessingLogic {

  @Override
  public Result process(Message message) {
    System.out.printf("%s starting processing message %s...%n", getClass().getName(), message.toString());
    try {
      Thread.sleep(100L);
      return Result.SUCCESS;
    } catch (Exception e) {
      System.out.printf("Exception occurred during %s processing of %s:\n %s%n", getClass().getName(), message, e.getMessage());
    }

    return Result.FAILURE;
  }
}
