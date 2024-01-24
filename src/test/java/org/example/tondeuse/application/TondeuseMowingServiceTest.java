package org.example.tondeuse.application;

import org.example.tondeuse.domain.models.Direction;
import org.example.tondeuse.domain.models.Pelouse;
import org.example.tondeuse.domain.models.Tondeuse;
import org.example.tondeuse.domain.ports.input.FileProcessingInputPort;
import org.example.tondeuse.domain.ports.output.FileProcessingOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class TondeuseMowingServiceTest {

    @Mock
    private FileProcessingInputPort fileInputAdapter;

    @Mock
    private FileProcessingOutputPort fileOutputAdapter;
    @InjectMocks
    private TondeuseMowingService tondeuseMowingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMowTondeuses() throws IOException {
        TondeuseMowingService tondeuseMowingService = new TondeuseMowingService(fileInputAdapter, fileOutputAdapter);

        Map<Tondeuse, String> tondeuseInstructionsMap = new LinkedHashMap<>();
        tondeuseInstructionsMap.put(new Tondeuse(3, 3, Direction.EAST, new Pelouse(5, 5)), "AADAADADDA");
        tondeuseInstructionsMap.put(new Tondeuse(1, 3, Direction.NORTH, new Pelouse(6, 5)), "AGA");
        when(fileInputAdapter.readFromFile()).thenReturn(tondeuseInstructionsMap);

        doNothing().when(fileOutputAdapter).writeFile(anyList());

        tondeuseMowingService.mowTondeuses();

        verify(fileInputAdapter, times(1)).readFromFile();
        verify(fileOutputAdapter, times(1)).writeFile(anyList());
    }
}
