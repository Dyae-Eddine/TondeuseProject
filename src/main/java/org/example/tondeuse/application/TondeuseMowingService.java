package org.example.tondeuse.application;

import org.example.tondeuse.domain.models.Tondeuse;
import org.example.tondeuse.domain.ports.input.FileProcessingInputPort;
import org.example.tondeuse.domain.ports.output.FileProcessingOutputPort;

import java.io.IOException;
import java.util.Map;

public class TondeuseMowingService {

    private final FileProcessingInputPort fileInputAdapter;
    private final FileProcessingOutputPort fileOutputAdapter;

    public TondeuseMowingService(FileProcessingInputPort fileInputAdapter,
                                      FileProcessingOutputPort fileOutputAdapter) {
        this.fileInputAdapter = fileInputAdapter;
        this.fileOutputAdapter = fileOutputAdapter;
    }

    /**
     * Reads Tondeuse instructions from the input file, executes mowing for each Tondeuse, and writes the final
     * positions to the output file (this method will be called from the Main).
     *
     * @throws IOException If an error occurs while reading from or writing to the files.
     */
    public void mowTondeuses() throws IOException {
        Map<Tondeuse, String> tondeuseInstructionsMap = fileInputAdapter.readFromFile();
        tondeuseInstructionsMap.forEach(Tondeuse::mow);
        fileOutputAdapter.writeFile(tondeuseInstructionsMap.keySet().stream().toList());
    }
}
