package org.codeus.patterns.case2;

import lombok.SneakyThrows;
import org.codeus.patterns.case2.core.Case2Client;
import org.codeus.patterns.case2.core.LocalService;
import org.codeus.patterns.case2.core.LocalServiceImpl;
import org.codeus.patterns.case2.external.ExternalService;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * A Reflection-based step by step test for a {@code case2} package.
 * PLEASE NOTE that Reflection API should not be used for testing a production code.
 * We use it for learning purposes only!
 */
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class Case2Test {

  @Order(1)
  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class MainFlowTest {

    LocalService spyLocalService = spy(new LocalServiceImpl());
    ExternalService spyExternalService = spy(new ExternalService());
    TimeProfiler spyTimeProfiler = spy(new OrangeTimeProfiler());
    Case2Client client = new Case2Client();

    @Test
    @Order(1)
    @DisplayName("LocalServiceImpl class should NOT have fields")
    void testLocalServiceImplClassDoesNotHaveFields() {
      Field[] declaredFields = LocalServiceImpl.class.getDeclaredFields();

      assertThat(declaredFields).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("LocalServiceImpl class should exactly one method")
    void testLocalServiceImplHasExactlyOneMethods() {
      Method[] declaredMethods = LocalServiceImpl.class.getDeclaredMethods();

      assertThat(declaredMethods).hasSize(1);
    }

    @Test
    @Order(3)
    @SneakyThrows
    @DisplayName("LocalServiceImpl class should have `process` method with no parameters")
    public void testLocalServiceImplProcessMethodParameters() {
      Method processMethod = LocalServiceImpl.class.getDeclaredMethod("process");

      assertNotNull(processMethod);
    }

    @Test
    @Order(6)
    @DisplayName("TimeProfiler method are called before ExternalService#process and before and after LocalService#process")
    void testTimeProfilingMethodsCalledInOrder() {
      client.doHeavyLifting(spyLocalService, spyTimeProfiler, spyExternalService);

      InOrder inOrder = Mockito.inOrder(spyLocalService, spyTimeProfiler, spyExternalService);

      inOrder.verify(spyExternalService).process(any(LocalService.class));
      inOrder.verify(spyTimeProfiler).startTimeProfiling();
      inOrder.verify(spyLocalService).process();
      inOrder.verify(spyTimeProfiler).endTimeProfiling();
    }

    @Test
    @Order(11)
    @DisplayName("TimeProfiler method are called are called exactly once")
    void testTimeProfilingMethodsCalledExactlyOnce() {
      client.doHeavyLifting(spyLocalService, spyTimeProfiler, spyExternalService);

      verify(spyExternalService).process(any(LocalService.class));
      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyLocalService).process();
      verify(spyTimeProfiler).endTimeProfiling();
    }
  }

  @Order(6)
  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class ExternalServiceTest {

    @Order(1)
    @Test
    @SneakyThrows
    public void testExternalServiceHasOnlyConstructor() {
      Class<?> aClass = ExternalService.class;
      Constructor<?>[] constructors = aClass.getDeclaredConstructors();

      assertEquals(1, constructors.length);
    }

    @Order(6)
    @Test
    public void testExternalServiceHasNoFields() {
      assertThat(ExternalService.class).hasOnlyDeclaredFields();
    }

    @Order(11)
    @Test
    @SneakyThrows
    public void testExternalServiceHasOnlyOneMethod() {
      Method[] declaredMethods = ExternalService.class.getDeclaredMethods();

      assertThat(declaredMethods).hasSize(1);
    }

    @Order(16)
    @Test
    @SneakyThrows
    public void testExternalServiceTheOnlyMethodHasLocalServiceAsArgument() {
      //TODO: hack, find better check
      try {
        ExternalService.class.getDeclaredMethod("process", LocalService.class);
      } catch (NoSuchMethodException e) {
        fail("%s should not be changed and have %s method with one argument with type %s".formatted(ExternalService.class.getName(), "process", LocalService.class.getName()));
      }
    }
  }
}
