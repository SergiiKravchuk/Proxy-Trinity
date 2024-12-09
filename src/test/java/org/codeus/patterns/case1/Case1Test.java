package org.codeus.patterns.case1;

import org.codeus.patterns.external.ExternalLibrariesTest;
import org.codeus.patterns.case1.core.ProcessingLogic;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.MemoryProfiler;
import org.codeus.patterns.external_libraries.poodle.PoodleMemoryProfiler;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * A Reflection-based step by step test for a {@code case1} package.
 * PLEASE NOTE that Reflection API should not be used for testing a production code.
 * We use it for learning purposes only!
 */
public class Case1Test extends ExternalLibrariesTest {

  @Order(1)
  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class MainFlowTest {

    ProcessingLogic processingLogic = new ProcessingLogic();
    MemoryProfiler memoryProfiler = new PoodleMemoryProfiler();
    TimeProfiler timeProfiler = new OrangeTimeProfiler();
    Case1Client client = new Case1Client();


    @Test
    @Order(1)
    void testProcessingLogicClassDoesNotHaveFields() {
      Field[] declaredFields = ProcessingLogic.class.getDeclaredFields();

      assertThat(declaredFields).isEmpty();
    }

    @Test
    @Order(6)
    void testMemoryProfilingMethodsCalledInOrder() {
      ProcessingLogic spyProcessingLogic = spy(processingLogic);
      MemoryProfiler spyMemoryProfiler = spy(memoryProfiler);

      client.doHeavyLifting(spyProcessingLogic, spyMemoryProfiler, timeProfiler);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyMemoryProfiler);

      inOrder.verify(spyMemoryProfiler).startMemoryProfiling();
      inOrder.verify(spyProcessingLogic).process();
      inOrder.verify(spyMemoryProfiler).endMemoryProfiling();
      inOrder.verify(spyMemoryProfiler).getResults();
    }

    @Test
    @Order(11)
    void testMemoryProfilingMethodsCalledExactNumberOfTimes() {
      ProcessingLogic spyProcessingLogic = spy(processingLogic);
      MemoryProfiler spyMemoryProfiler = spy(memoryProfiler);

      client.doHeavyLifting(spyProcessingLogic, spyMemoryProfiler, timeProfiler);

      verify(spyMemoryProfiler).startMemoryProfiling();
      verify(spyMemoryProfiler).endMemoryProfiling();
      verify(spyMemoryProfiler).getResults();
      verify(spyProcessingLogic, times(3)).process();
    }

    @Test
    @Order(16)
    void testTimeProfilingMethodsCalledInOrder() {
      ProcessingLogic spyProcessingLogic = spy(processingLogic);
      TimeProfiler spyTimeProfiler = spy(timeProfiler);

      client.doHeavyLifting(spyProcessingLogic, memoryProfiler, spyTimeProfiler);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyTimeProfiler);

      inOrder.verify(spyTimeProfiler).startTimeProfiling();
      inOrder.verify(spyProcessingLogic).process();
      inOrder.verify(spyTimeProfiler).endTimeProfiling();
    }

    @Test
    @Order(21)
    void testTimeProfilingMethodsCalledExactNumberOfTimes() {
      ProcessingLogic spyProcessingLogic = spy(processingLogic);
      TimeProfiler spyTimeProfiler = spy(timeProfiler);

      client.doHeavyLifting(spyProcessingLogic, memoryProfiler, spyTimeProfiler);

      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyTimeProfiler).endTimeProfiling();
      verify(spyProcessingLogic, times(3)).process();
    }


    @Test
    @Order(26)
    @DisplayName("RefactoredProcessingLogic should have 2 methods: 1 - process, 2 - one that incorporates profiling (e.g. processWithProfiling)")
    void testRefactoredProcessingLogicMethods() {
      Method[] declaredMethods = ProcessingLogic.class.getDeclaredMethods();

      //checking implicitly that the `process` method is present by other tests
      assertThat(declaredMethods).hasSize(2);
    }
  }
}
