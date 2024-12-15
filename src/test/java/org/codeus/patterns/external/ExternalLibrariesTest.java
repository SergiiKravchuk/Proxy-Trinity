package org.codeus.patterns.external;

import lombok.SneakyThrows;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ExternalLibrariesTest {

  @Order(21)
  @Nested
  @DisplayName("External Libraries Test")
  class NestedExternalLibrariesTest {

    @Nested
    class OrangeTimeProfilerTest {
      @Test
      @SneakyThrows
      public void testOrangeTimeProfilerFields() {
        Class<?> aClass = OrangeTimeProfiler.class;
        Field startTime = aClass.getDeclaredField("startTime");
        assertNotNull(startTime);
      }

      @Test
      public void testOrangeTimeProfilerFieldTypes() throws NoSuchFieldException {
        Class<?> aClass = OrangeTimeProfiler.class;
        Field startTime = aClass.getDeclaredField("startTime");
        assertNotNull(startTime);
        assertEquals(long.class, startTime.getType());
      }

      @Test
      @SneakyThrows
      public void testOrangeTimeProfilerHasOnlyOneConstructor() {
        Class<?> aClass = OrangeTimeProfiler.class;
        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        assertEquals(1, constructors.length);
      }

      @Test
      @SneakyThrows
      public void testOrangeTimeProfilerHasDefaultConstructor() {
        Class<?> aClass = OrangeTimeProfiler.class;
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        assertNotNull(constructor);
      }

      @SneakyThrows
      @ParameterizedTest
      @ValueSource(strings = {"startTimeProfiling", "endTimeProfiling"})
      public void testOrangeTimeProfilerMethodsExist(String methodName) {
        Class<?> aClass = OrangeTimeProfiler.class;
        Method method = aClass.getDeclaredMethod(methodName);
        assertNotNull(method);
      }

      @Test
      public void testOrangeTimeProfilerMethodsHaveCorrectSignature() throws NoSuchMethodException {
        Class<?> aClass = OrangeTimeProfiler.class;
        Method startTimeProfiling = aClass.getDeclaredMethod("startTimeProfiling");
        Method endTimeProfiling = aClass.getDeclaredMethod("endTimeProfiling");

        assertEquals(0, startTimeProfiling.getParameterCount());
        assertEquals(0, endTimeProfiling.getParameterCount());

        assertEquals(void.class, startTimeProfiling.getReturnType());
        assertEquals(long.class, endTimeProfiling.getReturnType());
      }

      @Test
      @SneakyThrows
      public void testOrangeTimeProfilerImplementsInterface() {
        Class<?> aClass = OrangeTimeProfiler.class;
        assertTrue(TimeProfiler.class.isAssignableFrom(aClass));
      }
    }
  }

}
