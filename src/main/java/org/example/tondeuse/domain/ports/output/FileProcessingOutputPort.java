package org.example.tondeuse.domain.ports.output;

import org.example.tondeuse.domain.models.Tondeuse;

import java.io.IOException;
import java.util.List;

public interface FileProcessingOutputPort {

    void writeFile(List<Tondeuse> tondeuses) throws IOException;
}
