package org.codeus.patterns.case2;

import org.codeus.patterns.case2.core.LocalServiceImpl;
import org.codeus.patterns.case2.external.ExternalService;
import org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler;

public class Case2Main {

  public static void main(String[] args) {
    new Case2Client().doHeavyLifting(new LocalServiceImpl(), new OrangeTimeProfiler(), new ExternalService());
  }
}
