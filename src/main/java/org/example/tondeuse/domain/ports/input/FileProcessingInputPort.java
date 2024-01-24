package org.example.tondeuse.domain.ports.input;

import org.example.tondeuse.domain.models.Tondeuse;

import java.io.IOException;
import java.util.Map;

public interface FileProcessingInputPort {

    Map<Tondeuse, String> readFromFile() throws IOException;
}
