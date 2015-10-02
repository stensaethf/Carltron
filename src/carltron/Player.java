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

import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Player is a wrapper class for the vehicle used the game: LightCycle. The
 * necessity of this class comes from the fact that a class has to be built
 * in order for advanced game features (i.e. bonus objects) to be used. Every
 * instantiation of this class, therefore, has to store an instance of
 * LightCycle and become bound with it. The class defines important variables
 * and methods for handling bonus checks such as the array bonuses and
 * HashMap counter.
 */
public class Player {

    /* instance variable field */
    protected LightCycle vehicle;
    protected int life;
    protected Bonus[] bonuses;
    protected HashMap<String, Integer> counter;

    /* constants are defined here */
    public static final int TURBO_DURATION = 12;
    public static final int JUMP_DURATION = 7;
    public static final int PROTECTOR_DURATION = 9;

    /**
     * Constructor. Takes in a LightCycle as argument and assigns it to
     * variable vehicle. Initializes array bonuses and HashMap counter, which
     * are important for handling bonus check.
     */
    public Player(LightCycle vehicle) {
        this.life = 3;
        this.vehicle = vehicle;
        this.bonuses = new Bonus[] {
                new Turbo(3, this.vehicle),
                new Jump(3, this.vehicle),
                new Protector(0, this.vehicle)
        };
        this.counter = new HashMap<>();
        // the initial counters for all three bonuses are 0
        this.counter.put("turbo",0);
        this.counter.put("jump",0);
        this.counter.put("protector",0);
    }


    /**
     * Returns the life of the player
     * @return the life of the player
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Returns the instance vehicle
     * @return the instance vehicle
     */
    public LightCycle getVehicle() {
        return this.vehicle;
    }

    /**
     * Decrement the life that the player has
     */
    public void decrementLife() {
        if (this.life > 0) {
            this.life--;
        }
    }

    /**
     * This method is called every time when updating animation. It checks
     * the current condition of player and its associated LightCycle.
     * Corresponding changes will be made based on the side effects of this
     * method.
     */
    public void statusCheck() {
        // right now we only need to check if any bonus is in effect
        bonusEffectCheck();
    }

    //******************* hasn't resolved the case where more than 1 bonuses
    // are used at the same time**************************
    protected void bonusEffectCheck() {
        // iterate over all bonus objects and check if we need to make any
        // change to corresponding counters
        for (String key : this.counter.keySet()) {
            // only increment the counter when a bonus is already consumed
            if (this.counter.get(key) != 0) {
                this.counter.put(key, this.counter.get(key)+1);
                switch (key) {
                    case "turbo":
                        if (this.counter.get(key) == TURBO_DURATION) {
                            this.vehicle.turnDefault();
                            // reset counter
                            this.counter.put(key, 0);
                        }
                        break;
                    case "jump":
                        if (this.counter.get(key) == JUMP_DURATION) {
                            this.vehicle.turnDefault();
                            // reset counter
                            this.counter.put(key, 0);
                        }
                        break;
                    case "protector":
                        if (this.counter.get(key) == PROTECTOR_DURATION) {
                            this.vehicle.turnDefault();
                            // reset counter
                            this.counter.put(key, 0);
                        }
                        break;
                    default: break;
                }
            }
        }
    }

    /**
     * This method is called every time a player hits a corresponding
     * item-consuming key. Calls the consume method of Bonus's subclass,
     * depending on the parameter "type"
     * @param type a string that indicates the type of bonus that's consumed.
     *             Possible values are "turbo", "jump" and "protector"
     *
     */
    public void consume(String type) {
        switch (type) {
            case "turbo":
                this.bonuses[0].consume();
                // starts to count the effect duration
                this.counter.put("turbo", 1);
                break;
            case "jump":
                this.bonuses[1].consume();
                // starts to count the effect duration
                this.counter.put("jump", 1);
                break;
            case "protector":
                this.bonuses[2].consume();
                // starts to count the effect duration
                this.counter.put("protector", 1);
                break;
            default: break;
        }
    }

    public void strategy() {}
}
