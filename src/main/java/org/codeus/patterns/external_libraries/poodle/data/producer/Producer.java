package org.codeus.patterns.external_libraries.poodle.data.producer;

import org.codeus.patterns.external_libraries.poodle.data.Message;

public interface Producer<T extends Message> {

  T produce();
}
