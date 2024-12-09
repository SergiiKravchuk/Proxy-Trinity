package org.codeus.patterns.case2.core;

import org.codeus.common.Result;

public class LocalServiceImpl implements LocalService {
  @Override
  public Result process() {
    System.out.printf("%s starting processing...%n", getClass().getName());
    try {
      Thread.sleep(100L);
      return Result.SUCCESS;
    } catch (Exception e) {
      System.out.printf("Exception occurred during %s processing:\n %s%n", getClass().getName(), e.getMessage());
    }

    return Result.FAILURE;
  }
}
