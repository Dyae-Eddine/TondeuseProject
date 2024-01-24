package org.example.tondeuse.infra.adapters;

import org.example.tondeuse.domain.models.Tondeuse;
import org.example.tondeuse.domain.ports.output.FileProcessingOutputPort;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileOutputAdapter implements FileProcessingOutputPort {

    /**
     * The value for this propertiy is typically stored in a properties file.
     * For simplicity reasons, I defined it here in the code.
     */
    private final String outputFilePath = "src/main/resources/output.txt";

    /**
     * Writes Tondeuse information to the output file.
     *
     * @param tondeuses The list of Tondeuses to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void writeFile(List<Tondeuse> tondeuses) throws IOException {
        // Write Tondeuse toString() to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            tondeuses.forEach(writer::println);
        } catch (IOException e) {
            throw new IOException("Error writing Tondeuse information to the file : " + outputFilePath +
                    "Please check file permissions and try again.");
        }
    }
}
