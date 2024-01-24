package org.example.tondeuse.infra.adapters.utils;

import org.example.tondeuse.domain.models.Direction;
import org.example.tondeuse.domain.models.Pelouse;
import org.example.tondeuse.domain.models.Tondeuse;

public class TondeuseProcessorUtils {

    /**
     * Processes a line of the file to extract Tondeuse position and direction information.
     *
     * @param line    A line of the file containing Tondeuse position and direction.
     * @param pelouse The Pelouse on which the Tondeuse operates.
     * @return A Tondeuse object with the specified position, direction, and associated Pelouse.
     * @throws NumberFormatException If the format of the line is incorrect or the dimensions are not valid numbers.
     */
    public static Tondeuse processTondeusePositionAndDirection(String line, Pelouse pelouse){
        // Split the line by space
        String[] parts = line.split("\\s+");
        if(parts.length == 3){
            try {
                return new Tondeuse(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])
                        , Direction.getNameByCode(parts[2].charAt(0)), pelouse);
            }catch(NumberFormatException e){
                throw new NumberFormatException("Invalid format of position and direction of the Tondeuse " +
                        "in the line of the file ");
            }
        }
        else{
            throw new NumberFormatException("The dimensions and direction in the file are incorrect");
        }
    }
}
