/**
 ************************ CARLTRON GAME****************************************
 * Made by  Derek Shang (shangd7)
 *          Frederik Ronn Stensaeth (stensaethf)
 *          Sabastian Mugazambi (mugazambis)
 *          Kiet Tran (trank)
 *          *******************************************************************
 *
 * Date : June 5 2015
 * @Purpose Software Design Course Final Project
 **/


package carltron;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class defines a type of Rectangle objects that represent the vehicle
 * used in the game. It stores variables that have to do with the states of
 * the object (velocities, leavesPath and shield, etc.). Most important
 * methods it has include step() and turnDefault().
 */
public class LightCycle extends Rectangle {
    /* instance variable field */
    private int velocityX;
    private int velocityY;
    private boolean leavesPath;
    private boolean shield;
    public int moved;

    /* constants are defined here */
    public static final int DEFAULT_VELOCITY = 1;
    public static final double DEFAULT_STEP_SIZE = 5;

    // constructor. Initializes the state of a LightCycle
    public LightCycle() {
        this.leavesPath = true;
        this.shield = false;
        this.moved = 0;
    }

    /**
     * Returns whether the LightCycle leaves path or not
     * @return a boolean leavesPath
     */
    public boolean hasPath() {
        return this.leavesPath;
    }

    /**
     * Sets the variable leavesPath
     * @param flag boolean type
     */
    public void setLeavesPath(boolean flag) {
        this.leavesPath = flag;
    }

    /**
     * Returns whether the LightCycle has shield or not
     * @return a boolean shield
     */
    public boolean hasShield() {
        return this.shield;
    }

    /**
     * Sets the variable shield
     * @param flag boolean type
     */
    public void setShield(boolean flag) {
        this.shield = flag;
    }

    // The following four methods are setters and getters for velocity
    // variables.
    public int getVelocityX() {
        return this.velocityX;
    }

    public void setVelocityX(int v) {
        this.velocityX = v;
    }

    public int getVelocityY() {
        return this.velocityY;
    }

    public void setVelocityY(int v) {
        this.velocityY = v;
    }

    /**
     * Updates the next position of LightCycle
     */
    public void step() {
        this.setLayoutX(this.getLayoutX() + this.velocityX * DEFAULT_STEP_SIZE);
        this.setLayoutY(this.getLayoutY() + this.velocityY * DEFAULT_STEP_SIZE);
    }

    /**
     * Turns the state of LightCycle back to initial. This includes resetting
     * the velocities and setting boolean type variables back to initial state.
     */
    public void turnDefault() {
        if (getVelocityX() < 0) {
            setVelocityX(-DEFAULT_VELOCITY);
        } else if (getVelocityX() > 0) {
            setVelocityX(DEFAULT_VELOCITY);
        } else if (getVelocityY() < 0) {
            setVelocityY(-DEFAULT_VELOCITY);
        } else if (getVelocityY() > 0) {
            setVelocityY(DEFAULT_VELOCITY);
        }

        this.leavesPath = true;
        this.shield = false;
    }
}
