package org.example.tondeuse.domain.models;

public enum Direction {

    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    private final char directionChar;

    Direction(char directionChar) {
        this.directionChar = directionChar;
    }

    public char getDirectionChar() {
        return directionChar;
    }

    public static Direction getNameByCode(char directionChar) {
        for (Direction direction : values()) {
            if (direction.getDirectionChar() == directionChar) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction code: " + directionChar);
    }

    /**
     * Changes the direction of a Tondeuse to the left based on its current direction.
     *
     * @param direction The current direction of the Tondeuse (N, E, S, or W).
     * @return The new direction after turning left.
     * @throws IllegalArgumentException If the provided direction is not recognized.
     */
    public static Direction changeDirectionToLeft(Direction direction) {
        return switch (direction) {
            case NORTH :
                yield  WEST;
            case EAST:
                yield  NORTH;
            case SOUTH:
                yield  EAST;
            case WEST:
                yield  SOUTH;
            default:
                throw new IllegalArgumentException("Invalid direction of Tondeuse, "
                        + "Ensure that the direction is valid (N, E, S, or W).");
        };
    }

    /**
     * Changes the direction of a Tondeuse to the right based on its current direction.
     *
     * @param direction The current direction of the Tondeuse (N, E, S, or W).
     * @return The new direction after turning right.
     * @throws IllegalArgumentException If the provided direction is not recognized.
     */
    public static Direction changeDirectionToRight(Direction direction) {
        return switch (direction) {
            case NORTH:
                yield EAST;
            case EAST:
                yield SOUTH;
            case SOUTH:
                yield WEST;
            case WEST:
                yield NORTH;
            default:
                throw new IllegalArgumentException("Invalid direction of Tondeuse, "
                        + "Ensure that the direction is valid (N, E, S, or W).");
        };
    }
}
