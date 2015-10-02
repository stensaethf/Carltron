package carltron;

import java.util.Random;

/**
 * AiPlayer.java
 * This class represents an AI player in 1 player mode. It defines basic
 * strategic methods to prevent path and edge collisions.
 */
public class AiPlayer extends Player {
    // instance variable field
    private Player human;
    private Grid grid;
    // generator is for generating random directions for the AI player
    private Random generator = new Random();

    // constructor. Automatically sets the associated vehicles and human player
    public AiPlayer(LightCycle vehicle, Player human, Grid grid) {
        super(vehicle);
        this.human = human;
        this.grid = grid;
    }


    /**
     * AI player makes decisions based on the current grid. This method is
     * called every time before AI player's step() is called.
     */
    public void strategy() {
        // avoids going right off the grid
        if (almostHitUp()) {
            turnLeft();
        } else if (almostHitDown()) {
            turnRight();
        } else if (almostHitLeft()) {
            turnDown();
        } else if (almostHitRight()) {
            turnUp();
        }

        // based on the next step, AI player makes corresonding moves to
        // avoid hitting paths.
        GridCell nextStep = grid.getCells()[(int)vehicle.getLayoutX() +
                10*vehicle.getVelocityX()][
                (int)vehicle.getLayoutY() + 10*vehicle.getVelocityY()];

        for (int i=0; i<4 && nextStep.isWall(); i++) {
            randomTurn();
        }
    }

    /**
     * Checks if AI is about to hip the edge
     * @return whether or not AI is about to hit the top edge
     */
    public boolean almostHitUp() {
        return vehicle.getVelocityY() == -1 && vehicle.getLayoutY() <= 5;
    }

    /**
     * Checks if AI is about to hip the edge
     * @return whether or not AI is about to hit the bottom edge
     */
    public boolean almostHitDown() {
        return vehicle.getVelocityY() == 1 &&
                vehicle.getLayoutY() >= grid.DEFAULT_HEIGHT-5;
    }


    /**
     * Checks if AI is about to hip the edge
     * @return whether or not AI is about to hit the left edge
     */
    public boolean almostHitLeft() {
        return vehicle.getVelocityX() == -1 && vehicle.getLayoutX() <= 5;
    }

    /**
     * Checks if AI is about to hip the edge
     * @return whether or not AI is about to hit the top edge
     */
    public boolean almostHitRight() {
        return vehicle.getVelocityX() == 1 &&
                vehicle.getLayoutX() >= grid.DEFAULT_WIDTH-5;
    }


    /**
     * Generates a random multiple of five in order for the AI to make decisions
     * @param min lower bound for the range
     * @param max upper bound for the range
     * @return a random integer between min and max that is a multiple of five
     */
    private int randInt(int min, int max) {
        return (generator.nextInt((max - min)+1)+min)*5;
    }

    /**
     * AI player makes a random change of directions
     */
    public void randomTurn() {
        int change = randInt(0,3);
        switch (change) {
            case 0: turnLeft(); break;
            case 5: turnRight(); break;
            case 10: turnUp(); break;
            case 15: turnDown(); break;
            default: turnUp(); break;
        }
    }

    /**
     * AI player turns left
     */
    public void turnLeft() {
        if (vehicle.getVelocityX() <= 0) {
            this.vehicle.setVelocityX(-1);
            this.vehicle.setVelocityY(0);
        }
    }

    /**
     * AI player turns right
     */
    public void turnRight() {
        if (vehicle.getVelocityX() >= 0) {
            this.vehicle.setVelocityX(1);
            this.vehicle.setVelocityY(0);
        }
    }

    /**
     * AI player turns up
     */
    public void turnUp() {
        if (vehicle.getVelocityY() <= 0) {
            this.vehicle.setVelocityX(0);
            this.vehicle.setVelocityY(-1);
        }
    }

    /**
     * AI player turns down
     */
    public void turnDown() {
        if (vehicle.getVelocityY() >= 0) {
            this.vehicle.setVelocityX(0);
            this.vehicle.setVelocityY(1);
        }
    }

}