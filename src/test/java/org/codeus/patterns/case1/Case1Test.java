package org.codeus.patterns.case1;

import lombok.SneakyThrows;
import org.codeus.patterns.case1.core.Case1Client;
import org.codeus.patterns.case1.core.profiler.Profiler;
import org.codeus.patterns.case1.core.profiler.SimpleTimeProfiler;
import org.codeus.patterns.case1.external.ExternalMemoryProfiler;
import org.codeus.patterns.case1.external.MemoryProfiler;
import org.codeus.patterns.case1.core.ProcessingLogic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * A Reflection-based step by step test for a {@code case1} package.
 * PLEASE NOTE that Reflection API should not be used for testing a production code.
 * We use it for learning purposes only!
 */
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class Case1Test {

  @Order(1)
  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class MainFlowTest {

    ProcessingLogic spyProcessingLogic = spy(new ProcessingLogic());
    MemoryProfiler spyMemoryProfiler = spy(new ExternalMemoryProfiler());
    Profiler spyTimeProfiler = spy(new SimpleTimeProfiler());
    Case1Client client = new Case1Client();

    @Test
    @Order(1)
    @DisplayName("ProcessingLogic class should NOT have fields")
    void testProcessingLogicClassDoesNotHaveFields() {
      Field[] declaredFields = ProcessingLogic.class.getDeclaredFields();

      assertThat(declaredFields).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("ProcessingLogic class should have 2 methods: 1 - process, 2 - processWithProfiling")
    void testProcessingLogicHasExactlyTwoMethods() {
      Method[] declaredMethods = ProcessingLogic.class.getDeclaredMethods();

      assertThat(declaredMethods).hasSize(2);
    }

    @Test
    @Order(3)
    @SneakyThrows
    @DisplayName("ProcessingLogic class should have `process` method with no parameters")
    public void testProcessingLogicProcessMethodParameters() {
      Method processMethod = ProcessingLogic.class.getDeclaredMethod("process");

      assertNotNull(processMethod);
    }

    @Test
    @Order(4)
    @SneakyThrows
    @DisplayName("ProcessingLogic class should have `processWithProfiling` method with one parameters of Profiler type")
    public void testProcessingLogicProcessWithProfilingMethodParameters() {
      Method processWithProfilingMethod = ProcessingLogic.class.getDeclaredMethod("processWithProfiling", Profiler.class);

      assertNotNull(processWithProfilingMethod);
    }

    @Test
    @Order(6)
    @DisplayName("MemoryProfiler method are called before and after ProcessingLogic#process")
    void testMemoryProfilingMethodsCalledInOrder() {
      client.doHeavyLifting(spyProcessingLogic, spyTimeProfiler, spyMemoryProfiler);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyMemoryProfiler);

      inOrder.verify(spyMemoryProfiler).startMemoryProfiling();
      inOrder.verify(spyProcessingLogic).process();
      inOrder.verify(spyMemoryProfiler).endMemoryProfiling();
      inOrder.verify(spyMemoryProfiler).getResults();
    }

    @Test
    @Order(11)
    @DisplayName("MemoryProfiler methods are called exactly once")
    void testMemoryProfilingMethodsCalledExactNumberOfTimes() {
      client.doHeavyLifting(spyProcessingLogic, spyTimeProfiler, spyMemoryProfiler);

      verify(spyMemoryProfiler).startMemoryProfiling();
      verify(spyMemoryProfiler).endMemoryProfiling();
      verify(spyMemoryProfiler).getResults();
    }

    @Test
    @Order(16)
    @DisplayName("TimeProfiler method are called before and after ProcessingLogic#process")
    void testTimeProfilingMethodsCalledInOrder() {
      client.doHeavyLifting(spyProcessingLogic, spyTimeProfiler, spyMemoryProfiler);

      InOrder inOrder = Mockito.inOrder(spyProcessingLogic, spyTimeProfiler);

      inOrder.verify(spyTimeProfiler).startProfiling();
      inOrder.verify(spyProcessingLogic).process();
      inOrder.verify(spyTimeProfiler).endProfiling();
    }

    @Test
    @Order(21)
    @DisplayName("TimeProfiler method are called are called exactly once")
    void testTimeProfilingMethodsCalledExactNumberOfTimes() {
      client.doHeavyLifting(spyProcessingLogic, spyTimeProfiler, spyMemoryProfiler);

      verify(spyTimeProfiler).startProfiling();
      verify(spyTimeProfiler).endProfiling();
    }
  }

  @Order(6)
  @Nested
  class ExternalMemoryProfilerTest {
    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = {"startMemoryFootprint", "results"})
    public void testExternalMemoryProfilerFieldsExist(String fieldName) {
      Class<?> aClass = ExternalMemoryProfiler.class;
      Field field = aClass.getDeclaredField(fieldName);
      assertNotNull(field);
    }

    @Test
    public void testExternalMemoryProfilerFieldsHaveCorrectType() throws NoSuchFieldException {
      Class<?> aClass = ExternalMemoryProfiler.class;
      Field startMemoryFootprint = aClass.getDeclaredField("startMemoryFootprint");
      Field results = aClass.getDeclaredField("results");

      assertEquals(double.class, startMemoryFootprint.getType());
      assertEquals(List.class, results.getType());
      Type genericType = results.getGenericType();
      if (genericType instanceof ParameterizedType pt) {
        assertEquals(String.class, pt.getActualTypeArguments()[0]);
      }
    }

    @Test
    @SneakyThrows
    public void testExternalMemoryProfilerConstructor() {
      Class<?> aClass = ExternalMemoryProfiler.class;
      Constructor<?>[] constructors = aClass.getDeclaredConstructors();
      assertEquals(1, constructors.length);
    }

    @Test
    @SneakyThrows
    public void testExternalMemoryProfilerHasDefaultConstructor() {
      Class<?> aClass = ExternalMemoryProfiler.class;
      Constructor<?> constructor = aClass.getDeclaredConstructor();
      assertNotNull(constructor);
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = {"startMemoryProfiling", "endMemoryProfiling", "getResults"})
    public void testExternalMemoryProfilerMethodsExist(String methodName) {
      Class<?> aClass = ExternalMemoryProfiler.class;
      Method method = aClass.getDeclaredMethod(methodName);
      assertNotNull(method);
    }

    @Test
    public void testExternalMemoryProfilerMethodsHaveCorrectSignature() throws NoSuchMethodException {
      Class<?> aClass = ExternalMemoryProfiler.class;
      Method startMemoryProfiling = aClass.getDeclaredMethod("startMemoryProfiling");
      Method endMemoryProfiling = aClass.getDeclaredMethod("endMemoryProfiling");
      Method getResults = aClass.getDeclaredMethod("getResults");

      assertEquals(0, startMemoryProfiling.getParameterCount());
      assertEquals(0, endMemoryProfiling.getParameterCount());
      assertEquals(0, getResults.getParameterCount());

      assertEquals(void.class, startMemoryProfiling.getReturnType());
      assertEquals(void.class, endMemoryProfiling.getReturnType());
      assertEquals(List.class, getResults.getReturnType());
    }

    @Test
    @SneakyThrows
    public void testExternalMemoryProfilerImplementsInterface() {
      Class<?> aClass = ExternalMemoryProfiler.class;
      assertTrue(MemoryProfiler.class.isAssignableFrom(aClass));
    }
  }
}
