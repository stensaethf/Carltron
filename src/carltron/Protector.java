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

/**
 * Created by shangd on 5/27/15.
 *
 * The Protector class is responsible for keeping track of how many more
 * shields is available to a LightCycle - the owner of the Protector object.
 */
public class Protector extends Bonus {

    /**
     * The constructor for the Protector class.
     * Takes an integer to represent the amount of shields available
     * (newAmount) to the LightCycle and sets amount to be this number.
     *
     **/
    public Protector(int amount, LightCycle owner) {
        super();
        if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
        this.owner = owner;
    }

    /**
     * Consume() consumes a potion of Protector and gains the ability to cross
     * a path and while the potion is in effect.
     *
     * @params n/a.
     * @return n/a.
     * **/
    @Override
    public void consume() {
        if (this.amount == 0) return;

        setAmount(this.amount - 1);
        this.owner.setShield(true);
    }

    // These are only used for testing.
    /**
     * getAmountTest() returns the amount that the Protector object is holding.
     * This method is only used for testing.
     *
     * @params n/a.
     * @return amount.
     * */
    public int getAmountTest() {
        return this.amount;
    }

    /**
     * getOwnerTest() returns the owner of the Protector object.
     * This method is only used for testing.
     *
     * @params n/a.
     * @return bike.
     * */
    public LightCycle getOwnerTest() {
        return this.owner;
    }
}
