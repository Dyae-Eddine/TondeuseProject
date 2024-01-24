package org.example.tondeuse.infra.adapters.utils;

import org.example.tondeuse.domain.models.Pelouse;

public class PelouseProcessorUtils {

    /**
     * Processes the first line of the file to extract Pelouse dimensions.
     *
     * @param line The first line of the file containing Pelouse dimensions.
     * @return A Pelouse object with the specified dimensions.
     * @throws NumberFormatException If the format of the first line is incorrect or the dimensions are not valid numbers.
     * @throws IllegalArgumentException If the first line of the file is null.
     */
    public static Pelouse processPelouseDimensions(String line){
        if(line != null) {
            // Split the line by space
            String[] partsOfLine = line.split("\\s+");
            if (partsOfLine.length == 2) {
                try {
                    return new Pelouse(Integer.parseInt(partsOfLine[0]), Integer.parseInt(partsOfLine[1]));
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid numeric format in the first line of the file");
                }
            }
            else {
                throw new NumberFormatException("The first line of the file must contain exactly two numbers");
            }
        }
        else {
            throw new IllegalArgumentException("The first line of the file is null");
        }
    }
}
