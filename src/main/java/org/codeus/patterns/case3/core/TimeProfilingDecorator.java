package org.codeus.patterns.case3.core;

import org.codeus.common.Result;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.data.Message;

public class TimeProfilingDecorator extends BaseDecorator {

  protected final TimeProfiler timeProfiler;

  public TimeProfilingDecorator(ProcessingLogic processingLogic, TimeProfiler timeProfiler) {
    super(processingLogic);
    this.timeProfiler = timeProfiler;
  }

  @Override
  public Result process(Message message) {
    timeProfiler.startTimeProfiling();
    Result result = super.process(message);
    long timeElapsed = timeProfiler.endTimeProfiling();
    System.out.printf("Time Elapsed:%d (nanoseconds)%n", timeElapsed);

    return result;
  }
}
