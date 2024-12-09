package org.codeus.patterns.external_libraries.poodle;

import java.util.List;

/**
 * An External interface, consider it as an interface of another module of your project or an external library,
 * so you don't have direct access to its sources.
 * This class SHOULD NOT BE altered in the course of this task.
 */
public interface MemoryProfiler {

  void startMemoryProfiling();
  void endMemoryProfiling();

  List<String> getResults();
}
