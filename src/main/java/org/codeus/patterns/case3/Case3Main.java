package org.codeus.patterns.case3;

import org.codeus.patterns.case3.core.LocalProcessingLogic;
import org.codeus.patterns.case3.external.PerformanceOrientedService;
import org.codeus.patterns.case3.external.ValidationOrientedService;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.poodle.data.producer.EventMessageProducer;
import org.codeus.patterns.external_libraries.poodle.data.validation.EventMessageValidator;

public class Case3Main {

  public static void main(String[] args) {
    Case3Client client = new Case3Client();
    client.doHeavyLiftingPart1(new LocalProcessingLogic(), new OrangeTimeProfiler(), new PerformanceOrientedService());
    client.doHeavyLiftingPart2(new LocalProcessingLogic(), new OrangeTimeProfiler(), new EventMessageValidator(), new ValidationOrientedService<>(new EventMessageProducer()));
  }
}
