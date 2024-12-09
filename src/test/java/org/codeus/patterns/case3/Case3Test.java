package org.codeus.patterns.case3;

import org.codeus.patterns.external.ExternalLibrariesTest;
import org.codeus.patterns.case3.core.LocalProcessingLogic;
import org.codeus.patterns.case3.external.PerformanceOrientedService;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.case3.external.ValidationOrientedService;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.data.EventMessage;
import org.codeus.patterns.external_libraries.poodle.data.TimestampMessage;
import org.codeus.patterns.external_libraries.poodle.data.producer.EventMessageProducer;
import org.codeus.patterns.external_libraries.poodle.data.producer.Producer;
import org.codeus.patterns.external_libraries.poodle.data.validation.EventMessageValidator;
import org.codeus.patterns.external_libraries.poodle.data.validation.Validator;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * A Reflection-based step by step test for a {@code case3} package.
 * PLEASE NOTE that Reflection API should not be used for testing a production code.
 * We use it for learning purposes only!
 */
public class Case3Test extends ExternalLibrariesTest {

  @Order(1)
  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class MainFlowTest {

    ProcessingLogic spyProcessingLogic = spy(new LocalProcessingLogic());
    TimeProfiler spyTimeProfiler = spy(new OrangeTimeProfiler());
    PerformanceOrientedService spyPerformanceOrientedService = spy(new PerformanceOrientedService());
    Producer<EventMessage> spyProducer = spy(new EventMessageProducer());
    Validator<EventMessage> spyValidator = spy(new EventMessageValidator());
    ValidationOrientedService<EventMessage> spyValidationOrientedService = spy(new ValidationOrientedService<>(spyProducer));

    Case3Client client = new Case3Client();

    @Test
    @Order(1)
    void testLocalServiceImplClassDoesNotHaveFields() {
      Field[] declaredFields = LocalProcessingLogic.class.getDeclaredFields();

      assertThat(declaredFields).isEmpty();
    }

    @Test
    @Order(6)
    void testPerformanceOrientedFlowMethodsCalledInOrder() {
      client.doHeavyLiftingPart1(spyProcessingLogic, spyTimeProfiler, spyPerformanceOrientedService);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyPerformanceOrientedService, spyTimeProfiler);

      inOrder.verify(spyPerformanceOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      inOrder.verify(spyTimeProfiler).startTimeProfiling();
      inOrder.verify(spyProcessingLogic).process(any(TimestampMessage.class));
      inOrder.verify(spyTimeProfiler).endTimeProfiling();
    }

    @Test
    @Order(11)
    void testPerformanceOrientedFlowMethodsCalledExactlyOnce() {
      client.doHeavyLiftingPart1(spyProcessingLogic, spyTimeProfiler, spyPerformanceOrientedService);

      verify(spyPerformanceOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyProcessingLogic).process(any(TimestampMessage.class));
      verify(spyTimeProfiler).endTimeProfiling();
    }
    @Test
    @Order(16)
    void testValidationOrientedFlowMethodsCalledInOrder() {
      client.doHeavyLiftingPart2(spyProcessingLogic, spyTimeProfiler, spyValidator, spyValidationOrientedService);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyTimeProfiler, spyValidator, spyValidationOrientedService);

      inOrder.verify(spyValidationOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      inOrder.verify(spyTimeProfiler).startTimeProfiling();
      inOrder.verify(spyValidator).validate(any(EventMessage.class));
      inOrder.verify(spyProcessingLogic).process(any(EventMessage.class));
      inOrder.verify(spyTimeProfiler).endTimeProfiling();
    }

    @Test
    @Order(21)
    void testValidationOrientedFlowCalledExactlyOnce() {
      client.doHeavyLiftingPart2(spyProcessingLogic, spyTimeProfiler, spyValidator, spyValidationOrientedService);

      verify(spyValidationOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyValidator).validate(any(EventMessage.class));
      verify(spyProcessingLogic).process(any(EventMessage.class));
      verify(spyTimeProfiler).endTimeProfiling();
    }
  }
}
