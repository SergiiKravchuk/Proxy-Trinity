package org.codeus.patterns.external;

import lombok.SneakyThrows;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.orange.TimeProfiler;
import org.codeus.patterns.external_libraries.poodle.MemoryProfiler;
import org.codeus.patterns.external_libraries.poodle.PoodleMemoryProfiler;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ExternalLibrariesTest {

  @Order(21)
  @Nested
  @DisplayName("External Libraries Test")
  class NestedExternalLibrariesTest {

    @Nested
    class PoodleMemoryProfilerTest {
      @SneakyThrows
      @ParameterizedTest
      @ValueSource(strings = {"startMemoryFootprint", "results"})
      public void testPoodleMemoryProfilerFields(String fieldName) {
        Class<?> aClass = PoodleMemoryProfiler.class;
        Field field = aClass.getDeclaredField(fieldName);
        assertNotNull(field);
      }

      @Test
      @SneakyThrows
      public void testPoodleMemoryProfilerConstructor() {
        Class<?> aClass = PoodleMemoryProfiler.class;
        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        assertEquals(1, constructors.length);
      }

      @Test
      @SneakyThrows
      public void testPoodleMemoryProfilerHasDefaultConstructor() {
        Class<?> aClass = PoodleMemoryProfiler.class;
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        assertNotNull(constructor);
      }

      @SneakyThrows
      @ParameterizedTest
      @ValueSource(strings = {"startMemoryProfiling", "endMemoryProfiling", "getResults"})
      public void testPoodleMemoryProfilerMethods(String methodName) {
        Class<?> aClass = PoodleMemoryProfiler.class;
        Method method = aClass.getDeclaredMethod(methodName);
        assertNotNull(method);
      }

      @Test
      //TODO: review, finalize
      public void testPoodleMemoryProfilerFields() throws NoSuchFieldException {
        Class<?> aClass = PoodleMemoryProfiler.class;
        Field startMemoryFootprint = aClass.getDeclaredField("startMemoryFootprint");
        Field results = aClass.getDeclaredField("results");
        assertNotNull(startMemoryFootprint);
        assertNotNull(results);
        assertEquals(double.class, startMemoryFootprint.getType());
        assertEquals(List.class, results.getType());
        Type genericType = results.getGenericType();
        if (genericType instanceof ParameterizedType) {
          ParameterizedType pt = (ParameterizedType) genericType;
          assertEquals(String.class, pt.getActualTypeArguments()[0]);
        }
      }

      @Test
      public void testPoodleMemoryProfilerMethods() throws NoSuchMethodException {
        Class<?> aClass = org.codeus.patterns.external_libraries.poodle.PoodleMemoryProfiler.class;
        Method startMemoryProfiling = aClass.getDeclaredMethod("startMemoryProfiling");
        Method endMemoryProfiling = aClass.getDeclaredMethod("endMemoryProfiling");
        Method getResults = aClass.getDeclaredMethod("getResults");
        assertNotNull(startMemoryProfiling);
        assertNotNull(endMemoryProfiling);
        assertNotNull(getResults);
        assertEquals(void.class, startMemoryProfiling.getReturnType());
        assertEquals(void.class, endMemoryProfiling.getReturnType());
        assertEquals(List.class, getResults.getReturnType());
      }

      @Test
      @SneakyThrows
      public void testPoodleMemoryProfilerImplementsInterface() {
        Class<?> aClass = PoodleMemoryProfiler.class;
        assertTrue(MemoryProfiler.class.isAssignableFrom(aClass));
      }
    }

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
      public void testOrangeTimeProfilerMethods(String methodName) {
        Class<?> aClass = OrangeTimeProfiler.class;
        Method method = aClass.getDeclaredMethod(methodName);
        assertNotNull(method);
      }

      @Test
      public void testOrangeTimeProfilerMethods() throws NoSuchMethodException {
        Class<?> aClass = OrangeTimeProfiler.class;
        Method startTimeProfiling = aClass.getDeclaredMethod("startTimeProfiling");
        Method endTimeProfiling = aClass.getDeclaredMethod("endTimeProfiling");
        assertNotNull(startTimeProfiling);
        assertNotNull(endTimeProfiling);
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
