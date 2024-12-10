package org.codeus.patterns.case3.core;

import org.codeus.common.Result;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.external_libraries.poodle.data.Message;
import org.codeus.patterns.external_libraries.poodle.data.filter.Filter;

public class MessageFilteringDecorator<T extends Message> extends BaseDecorator {

  protected final Filter<T> messageFilter;

  public MessageFilteringDecorator(ProcessingLogic processingLogic, Filter<T> messageFilter) {
    super(processingLogic);
    this.messageFilter = messageFilter;
  }

  @Override
  public Result process(Message message) {
    boolean shouldBePreserved = messageFilter.filter((T) message);
    if (shouldBePreserved) return super.process(message);
    else {
      System.out.printf("%s cannot be processed, skipping %n", message.toString());
      return Result.SKIPPED;
    }
  }
}
