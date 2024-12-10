package org.codeus.patterns.case3.core;

import org.codeus.common.Result;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.external_libraries.poodle.data.Message;

public abstract class BaseDecorator implements ProcessingLogic {

  protected final ProcessingLogic processingLogic;

  public BaseDecorator(ProcessingLogic processingLogic) {
    this.processingLogic = processingLogic;
  }

  @Override
  public Result process(Message message) {
    return processingLogic.process(message);
  }
}
