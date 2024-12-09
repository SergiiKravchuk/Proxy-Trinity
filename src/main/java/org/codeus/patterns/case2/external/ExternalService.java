package org.codeus.patterns.case2.external;

import org.codeus.patterns.case2.core.LocalService;

/**
 * An External class, consider it as a class in another module of your project or an external library,
 * so you don't have direct access to its sources
 * This class SHOULD NOT BE altered in the course of this task.
 */
public class ExternalService {

  public void process(LocalService localService) {
    System.out.printf("%s starting processing...%n", getClass().getName());
    localService.process();
    System.out.printf("%s ending processing...%n", getClass().getName());
  }
}
