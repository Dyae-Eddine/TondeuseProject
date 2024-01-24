package org.example.tondeuse.domain.models;

import java.util.Objects;

public class Tondeuse {
    private int x;
    private int y;
    private Direction direction;
    private Pelouse pelouse;

    /**
     * Constructs a Tondeuse object with specified position, direction, and associated Pelouse.
     *
     * @param x         The x-coordinate of the Tondeuse position on the Pelouse.
     * @param y         The y-coordinate of the Tondeuse position on the Pelouse.
     * @param direction The initial direction the Tondeuse is facing (N, E, S, or W).
     * @param pelouse   The Pelouse on which the Tondeuse operates.
     * @throws NumberFormatException If the provided coordinates are less than zero or greater than the
     *          Pelouse dimensions or if the direction is invalid.
     */

    public Tondeuse(int x, int y, Direction direction, Pelouse pelouse) {
        if(verifyXAndY(x, y, pelouse) && verifyDirection(direction)) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.pelouse = pelouse;
        }
        else{
            throw new NumberFormatException("Invalid Tondeuse initialization. " +
                    "Ensure that x and y are not negative, " +
                    "within the Pelouse boundaries, " +
                    "and the direction is valid (N, E, S, or W).");
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x >= 0 && x <= this.pelouse.x())
            this.x = x;
        else
            throw new NumberFormatException("Invalid x coordinate. Ensure that x is not negative, " +
                    "and within the Pelouse boundaries");
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y >= 0 && y <= this.pelouse.y())
            this.y = y;
        else
            throw new NumberFormatException("Invalid y coordinate. Ensure that y is not negative, " +
                    "and within the Pelouse boundaries");
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if(verifyDirection(direction))
            this.direction = direction;
        else
            throw new IllegalArgumentException("Invalid direction of Tondeuse, "
                    + "Ensure that the direction is valid (N, E, S, or W).");
    }

    public Pelouse getPelouse() {
        return pelouse;
    }

    public void setPelouse(Pelouse pelouse) {
        this.pelouse = pelouse;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction.getDirectionChar();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tondeuse tondeuse = (Tondeuse) o;
        return x == tondeuse.x && y == tondeuse.y && direction == tondeuse.direction && Objects.equals(pelouse, tondeuse.pelouse);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y, direction);
        result = 31 * result + pelouse.hashCode();
        return result;
    }

    /**
     * Verifies if the provided coordinates (x, y) are within the boundaries of the Pelouse.
     *
     * @param x The x-coordinate to be verified.
     * @param y The y-coordinate to be verified.
     * @param pelouse The Pelouse object representing the boundaries.
     * @return true if the coordinates are within the Pelouse boundaries, false otherwise.
     */
    private boolean verifyXAndY(int x, int y, Pelouse pelouse){
        return x >= 0 && y >= 0 && x <= pelouse.x() && y <= pelouse.y();
    }
    /**
     * Verifies if the given direction is valid (N, E, S, or W).
     *
     * @param direction The character representing the direction to be verified.
     * @return true if the direction is valid, and false otherwise.
     */
    public boolean verifyDirection(Direction direction){
        return direction.getDirectionChar() == Direction.EAST.getDirectionChar()
                || direction.getDirectionChar() == Direction.SOUTH.getDirectionChar()
                || direction.getDirectionChar() == Direction.WEST.getDirectionChar()
                || direction.getDirectionChar() == Direction.NORTH.getDirectionChar();
    }

    /**
     * Executes mowing instructions by iterating through each character in the given string.
     *
     * @param instructions A string containing mowing instructions.
     */
    public void mow(String instructions){
        for(char c : instructions.toCharArray()){
            this.move(c);
        }
    }

    /**
     * Performs a move operation based on the given instruction character.
     *
     * @param c The instruction character ('A' for advancing, 'G' for turning left, 'D' for turning right).
     * @throws IllegalArgumentException If the provided instruction character is invalid.
     */
    private void move(char c){
        switch(c){
            case 'A' :
                moveToNewPosition();
                break;
            case 'G' :
                this.direction = Direction.changeDirectionToLeft(this.direction);
                break;
            case 'D' :
                this.direction = Direction.changeDirectionToRight(this.direction);
                break;
            default:
                throw new IllegalArgumentException("Invalid move instruction. " +
                        "Use 'A' for advancing, 'G' for turning left, or 'D' for turning right.");
        }
    }

    /**
     * Moves the Tondeuse to a new position based on its current direction.
     */
    private void moveToNewPosition(){
        switch (this.direction) {
            case Direction.NORTH :
                goToY(this.y + 1);
                break;
            case Direction.EAST :
                goToX(this.x + 1);
                break;
            case Direction.SOUTH :
                goToY(this.y - 1);
                break;
            case Direction.WEST :
                goToX(this.x - 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction of Tondeuse, " +
                        "Ensure that the direction is valid (N, E, S, or W).");
        }
    }

    /**
     * Updates the y if the new position is within the Pelouse boundaries.
     *
     * @param newPosition The new y-coordinate for the Tondeuse.
     */
    private void goToY(int newPosition){
        if(newPosition <= this.pelouse.y() && newPosition >= 0)
            this.y = newPosition;

    }

    /**
     * Updates the x if the new position is within the Pelouse boundaries.
     *
     * @param newPosition The new x-coordinate for the Tondeuse.
     */
    private void goToX(int newPosition){
        if(newPosition <= this.pelouse.x() && newPosition >= 0)
            this.x = newPosition;
    }

}
