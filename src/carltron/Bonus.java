package carltron;
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

/**
 * An abstract class that serves as the parent of important in-game bonus
 * objects (Turbo, Jump and Protector). Defines a dummy constructor and basic
 * getter and setter methods. The essence of this class is the method consume
 * (), which should be overwritten by its subclasses.
 */
public abstract class Bonus {
    /* instance variable field */
    protected int amount;
    protected LightCycle owner;

    // dummy constructor that sets the amount of bonus item 0
    public Bonus() {
        this.amount = 0;
    }

    /**
     * Returns the amount of bonus
     * @return the amount of bonus
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount of bonus
     * @param nAmount new amount
     */
    public void setAmount(int nAmount) {
        this.amount = nAmount;
    }

    /**
     * consume means different things for different subclasses. Therefore
     * this method must be overwritten and redefined by subclasses.
     */
    public abstract void consume();

}