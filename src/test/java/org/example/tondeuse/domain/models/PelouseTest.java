package org.example.tondeuse.domain.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PelouseTest {

    @Test
    public void testConstruct_validDimensions() {
        Pelouse pelouse = new Pelouse(5, 5);

        assertEquals(5, pelouse.x());
    }

    @Test
    public void testConstruct_invalidX() {

        assertThrows(NumberFormatException.class,() -> new Pelouse(-1, 5));
    }

    @Test
    public void testConstruct_invalidY() {

        assertThrows(NumberFormatException.class,() -> new Pelouse(5, -2));
    }
}
