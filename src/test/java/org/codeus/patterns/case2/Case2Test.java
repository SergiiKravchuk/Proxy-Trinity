package org.codeus.patterns.case2;

import lombok.SneakyThrows;
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

    LocalService localService = new LocalServiceImpl();
    ExternalService externalService = new ExternalService();
    TimeProfiler timeProfiler = new OrangeTimeProfiler();
    Case2Client client = new Case2Client();

    @Test
    @Order(1)
    void testLocalServiceImplClassDoesNotHaveFields() {
      Field[] declaredFields = LocalServiceImpl.class.getDeclaredFields();

      assertThat(declaredFields).isEmpty();
    }

    @Test
    @Order(6)
    void testTimeProfilingMethodsCalledInOrder() {
      LocalService spyLocalService = spy(localService);
      TimeProfiler spyTimeProfiler = spy(timeProfiler);
      ExternalService spyExternalService = spy(externalService);

      client.doHeavyLifting(spyLocalService, spyTimeProfiler, spyExternalService);

      InOrder inOrder = Mockito.inOrder(spyLocalService, spyTimeProfiler, spyExternalService);

      inOrder.verify(spyExternalService).process(any(LocalService.class));
      inOrder.verify(spyTimeProfiler).startTimeProfiling();
      inOrder.verify(spyLocalService).process();
      inOrder.verify(spyTimeProfiler).endTimeProfiling();
    }

    @Test
    @Order(11)
    void testTimeProfilingMethodsCalledExactlyOnce() {
      LocalService spyLocalService = spy(localService);
      TimeProfiler spyTimeProfiler = spy(timeProfiler);
      ExternalService spyExternalService = spy(externalService);

      client.doHeavyLifting(spyLocalService, spyTimeProfiler, spyExternalService);

      verify(spyExternalService).process(any(LocalService.class));
      verify(spyTimeProfiler).startTimeProfiling();
      verify(spyLocalService).process();
      verify(spyTimeProfiler).endTimeProfiling();
    }
  }

  @Order(5)
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
