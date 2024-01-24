package org.example.tondeuse.domain.models;

public record Pelouse(int x, int y) {

    /**
     * Constructs a Pelouse object with the specified dimensions.
     *
     * @param x The width of the Pelouse.
     * @param y The height of the Pelouse.
     * @throws NumberFormatException If the provided dimensions (x or y) are less than zero.
     */
    public Pelouse{
        if(x < 0 || y < 0)
            throw new NumberFormatException("The x and y can not be inferior than zero");
    }
}
