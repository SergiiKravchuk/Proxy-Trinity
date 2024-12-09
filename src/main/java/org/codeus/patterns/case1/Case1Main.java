package org.codeus.patterns.case1;

import org.codeus.patterns.case1.core.ProcessingLogic;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;
import org.codeus.patterns.external_libraries.poodle.PoodleMemoryProfiler;

public class Case1Main {

  public static void main(String[] args) {
    new Case1Client().doHeavyLifting(new ProcessingLogic(), new PoodleMemoryProfiler(), new OrangeTimeProfiler());
  }
}
