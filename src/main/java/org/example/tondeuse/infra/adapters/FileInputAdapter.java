package org.example.tondeuse.infra.adapters;

import org.example.tondeuse.domain.models.Pelouse;
import org.example.tondeuse.domain.models.Tondeuse;
import org.example.tondeuse.domain.ports.input.FileProcessingInputPort;
import org.example.tondeuse.infra.adapters.utils.PelouseProcessorUtils;
import org.example.tondeuse.infra.adapters.utils.TondeuseProcessorUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileInputAdapter implements FileProcessingInputPort {

    /**
     * The value for this propertiy is typically stored in a properties file.
     * For simplicity reasons, I defined it here in the code.
     */
    private final String inputFilePath = "src/main/resources/tondeuses.txt";

    /**
     * Read Pelouse and Tondeuse information from the input file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public Map<Tondeuse, String> readFromFile() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))){
            return processfile(reader);
        } catch (IOException e){
            throw new IOException("Error reading Tondeuse information from the file : " + inputFilePath +
                    "Please check file permissions and try again.");
        }
    }

    /**
     * Processes the content of a file to extract Pelouse dimensions and Tondeuse instructions.
     *
     * @param reader The BufferedReader for reading the file content.
     * @return A Map associating each Tondeuse with its corresponding instructions.
     * @throws IOException If an I/O error occurs while processing the file or if the file format is incorrect.
     */
    private Map<Tondeuse, String> processfile(BufferedReader reader) throws IOException {
        Pelouse pelouse;
        Map<Tondeuse, String> mapTondeuseInstructions = new LinkedHashMap<>();
        try{
            // Process the first line of the file that contains the Pelouse dimensions
            String firstLine = reader.readLine();
            pelouse = PelouseProcessorUtils.processPelouseDimensions(firstLine);

            // Process the rest of the file two lines by two lines
            String line1, line2;
            while ((line1 = reader.readLine()) != null && (line2 = reader.readLine()) != null) {
                mapTondeuseInstructions.put(TondeuseProcessorUtils.processTondeusePositionAndDirection(line1, pelouse)
                        , line2);
            }

        } catch (IOException e){
            throw new IOException("Error processing the file. Please check the file format and content for correctness.");
        }
        return mapTondeuseInstructions;
    }
}
