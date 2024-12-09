package org.codeus.patterns.case3.external;

import org.codeus.common.Result;
import org.codeus.patterns.external_libraries.poodle.data.Message;

/**
 * An External interface, consider it as an interface of another module of your project or an external library,
 * so you don't have direct access to its sources.
 * This class SHOULD NOT BE altered in the course of this task.
 */
public interface ProcessingLogic {

  Result process(Message message);
}
