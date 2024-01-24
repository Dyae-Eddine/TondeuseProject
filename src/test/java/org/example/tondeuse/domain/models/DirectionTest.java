package org.example.tondeuse.domain.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    @Test
    public void testGetNameByCode() {
        assertEquals(Direction.NORTH, Direction.getNameByCode('N'));
        assertEquals(Direction.EAST, Direction.getNameByCode('E'));
        assertEquals(Direction.SOUTH, Direction.getNameByCode('S'));
        assertEquals(Direction.WEST, Direction.getNameByCode('W'));
    }

    @Test
    public void testChangeDirectionToLeft() {
        assertEquals(Direction.WEST, Direction.changeDirectionToLeft(Direction.NORTH));
        assertEquals(Direction.NORTH, Direction.changeDirectionToLeft(Direction.EAST));
        assertEquals(Direction.EAST, Direction.changeDirectionToLeft(Direction.SOUTH));
        assertEquals(Direction.SOUTH, Direction.changeDirectionToLeft(Direction.WEST));
    }

    @Test
    public void testChangeDirectionToRight() {
        assertEquals(Direction.EAST, Direction.changeDirectionToRight(Direction.NORTH));
        assertEquals(Direction.SOUTH, Direction.changeDirectionToRight(Direction.EAST));
        assertEquals(Direction.WEST, Direction.changeDirectionToRight(Direction.SOUTH));
        assertEquals(Direction.NORTH, Direction.changeDirectionToRight(Direction.WEST));
    }
}
