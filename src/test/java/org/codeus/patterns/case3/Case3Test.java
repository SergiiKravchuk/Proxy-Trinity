package org.codeus.patterns.case3;

import lombok.SneakyThrows;
import org.codeus.patterns.case3.core.Case3Client;
import org.codeus.patterns.case3.core.LocalProcessingLogic;
import org.codeus.patterns.case3.external.DataConsistencyOrientedService;
import org.codeus.patterns.case3.external.PerformanceOrientedService;
import org.codeus.patterns.case3.external.ProcessingLogic;
import org.codeus.patterns.external.ExternalLibrariesTest;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.data.EventMessage;
import org.codeus.patterns.external_libraries.poodle.data.Message;
import org.codeus.patterns.external_libraries.poodle.data.TimestampMessage;
import org.codeus.patterns.external_libraries.poodle.data.filter.EventMessageFilter;
import org.codeus.patterns.external_libraries.poodle.data.filter.Filter;
import org.codeus.patterns.external_libraries.poodle.data.producer.EventMessageProducer;
import org.codeus.patterns.external_libraries.poodle.data.producer.Producer;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    Filter<EventMessage> spyFilter = spy(new EventMessageFilter());
    DataConsistencyOrientedService<EventMessage> spyDataConsistencyOrientedService = spy(new DataConsistencyOrientedService<>(spyProducer));

    Case3Client client = new Case3Client();

    @Test
    @Order(1)
    @DisplayName("LocalProcessingLogic class should NOT have fields")
    void testLocalServiceImplClassDoesNotHaveFields() {
      Field[] declaredFields = LocalProcessingLogic.class.getDeclaredFields();

      assertThat(declaredFields).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("LocalProcessingLogic class should exactly one method")
    void testLocalProcessingLogicHasExactlyOneMethods() {
      Method[] declaredMethods = LocalProcessingLogic.class.getDeclaredMethods();

      assertThat(declaredMethods).hasSize(1);
    }

    @Test
    @Order(3)
    @SneakyThrows
    @DisplayName("LocalProcessingLogic class should have `process` method with one parameter of type Message")
    public void testLocalProcessingLogicProcessMethodParameters() {
      Method processMethod = LocalProcessingLogic.class.getDeclaredMethod("process", Message.class);

      assertNotNull(processMethod);
    }

    @Test
    @Order(6)
    @DisplayName("Performance Oriented Service Flow methods are called before PerformanceOrientedService#doHeavyLifting and before and after LocalProcessingLogic#process")
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
    @DisplayName("Performance Oriented Service Flow methods are called exactly once")
    void testPerformanceOrientedFlowMethodsCalledExactlyOnce() {
      client.doHeavyLiftingPart1(spyProcessingLogic, spyTimeProfiler, spyPerformanceOrientedService);

      verify(spyPerformanceOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyProcessingLogic).process(any(TimestampMessage.class));
      verify(spyTimeProfiler).endTimeProfiling();
    }
    @Test
    @Order(16)
    @DisplayName("Data Consistency Oriented Service Flow methods are called before DataConsistencyOrientedService#doHeavyLifting and before and after LocalProcessingLogic#process")
    void testDataConsistencyOrientedFlowMethodsCalledInOrder() {
      client.doHeavyLiftingPart2(spyProcessingLogic, spyTimeProfiler, spyFilter, spyDataConsistencyOrientedService);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyTimeProfiler, spyFilter, spyDataConsistencyOrientedService);

      inOrder.verify(spyDataConsistencyOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      inOrder.verify(spyTimeProfiler).startTimeProfiling();
      inOrder.verify(spyFilter).filter(any(EventMessage.class));
      inOrder.verify(spyProcessingLogic).process(any(EventMessage.class));
      inOrder.verify(spyTimeProfiler).endTimeProfiling();
    }

    @Test
    @Order(21)
    @DisplayName("Data Consistency Oriented Service Flow methods are called exactly once")
    void testDataConsistencyOrientedFlowCalledExactlyOnce() {
      client.doHeavyLiftingPart2(spyProcessingLogic, spyTimeProfiler, spyFilter, spyDataConsistencyOrientedService);

      verify(spyDataConsistencyOrientedService).doHeavyLifting(any(ProcessingLogic.class));
      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyFilter).filter(any(EventMessage.class));
      verify(spyProcessingLogic).process(any(EventMessage.class));
      verify(spyTimeProfiler).endTimeProfiling();
    }
  }
}
