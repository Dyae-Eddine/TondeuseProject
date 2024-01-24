package org.example.tondeuse.infra.adapters;

import org.example.tondeuse.domain.models.Tondeuse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileInputAdapterTest {

    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private FileInputAdapter fileInputAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadFromFile_validInstructionsAndDimensions() throws IOException {

        when(bufferedReader.readLine())
                .thenReturn("5 5")
                .thenReturn("1 2 N")
                .thenReturn("GAGAGAGAA")
                .thenReturn("3 3 E")
                .thenReturn("AADAADADDA")
                .thenReturn(null);

        Map<Tondeuse, String> result = fileInputAdapter.readFromFile();
        Map.Entry<Tondeuse, String> firstEntry = result.entrySet().iterator().next();
        Tondeuse tondeuse = firstEntry.getKey();
        String instructions = firstEntry.getValue();

        assertEquals(2, result.size());
        assertEquals(5, tondeuse.getPelouse().x());
        assertEquals("GAGAGAGAA", instructions);
    }
}
