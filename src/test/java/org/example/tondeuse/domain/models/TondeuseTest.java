package org.example.tondeuse.domain.models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TondeuseTest {

    @Test
    public void testConstruct_validDimensions_notValidXCoordinate() {
        Pelouse pelouse = new Pelouse(5, 5);

        assertThrows(NumberFormatException.class, () -> new Tondeuse(-1, 3, Direction.NORTH, pelouse));
    }

    @Test
    public void testConstruct_validDimensions_yCoordinateInferiorThanZero() {
        Pelouse pelouse = new Pelouse(5, 5);

        assertThrows(NumberFormatException.class, () -> new Tondeuse(1, -3, Direction.NORTH, pelouse));
    }

    @Test
    public void testConstruct_validDimensions_YCoordinateGreaterThanPelouse() {
        Pelouse pelouse = new Pelouse(5, 5);

        assertThrows(NumberFormatException.class, () -> new Tondeuse(1, 7, Direction.NORTH, pelouse));
    }

    @Test
    public void testConstruct_validDimensions_XCoordinateGreaterThanPelouse() {
        Pelouse pelouse = new Pelouse(5, 5);

        assertThrows(NumberFormatException.class, () -> new Tondeuse(6, 3, Direction.NORTH, pelouse));
    }

    @Test
    public void testSetCoordinates() {
        Pelouse pelouse = new Pelouse(5, 5);
        Tondeuse tondeuse = new Tondeuse(2, 3, Direction.NORTH, pelouse);

        tondeuse.setX(4);
        tondeuse.setY(1);

        assertEquals(4, tondeuse.getX());
        assertEquals(1, tondeuse.getY());
    }

    @Test
    public void testSet_invalidXCoordinate() {
        Pelouse pelouse = new Pelouse(5, 5);
        Tondeuse tondeuse = new Tondeuse(2, 3, Direction.NORTH, pelouse);
        assertThrows(NumberFormatException.class, () -> tondeuse.setX(6));
    }

    @Test
    public void testSet_invalidYCoordinate() {
        Pelouse pelouse = new Pelouse(5, 5);
        Tondeuse tondeuse = new Tondeuse(2, 3, Direction.NORTH, pelouse);
        assertThrows(NumberFormatException.class, () -> tondeuse.setY(6));
    }

    @Test
    public void testMow_notValidInstructions() {
        Pelouse pelouse = new Pelouse(5, 5);
        Tondeuse tondeuse = new Tondeuse(1, 3, Direction.NORTH, pelouse);

        assertThrows(IllegalArgumentException.class, () -> tondeuse.mow("GAGAGYGAA"));
    }

    @Test
    public void testMow_validInstructions() {
        Pelouse pelouse = new Pelouse(5, 5);
        Tondeuse tondeuse = new Tondeuse(1, 2, Direction.NORTH, pelouse);

        tondeuse.mow("GAGAGAGAA");

        assertEquals(1, tondeuse.getX());
        assertEquals(3, tondeuse.getY());
        assertEquals(Direction.NORTH, tondeuse.getDirection());
    }

}
