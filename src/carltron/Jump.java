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
 * The Jump class is responsible for keeping track of how many more jumps is
 * available to a LightCycle - the owner of the Jump object.
 */
public class Jump extends Bonus {

    /**
     * The constructor for the jump class.
     * Takes an integer to represent the amount of jumps available (newAmount)
     * to the LightCycle and sets amount to be this number.
     *
     **/
    public Jump(int amount, LightCycle owner) {
        super();
        if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
        this.owner = owner;
    }

    /**
     * Consume() consumes a potion of jump and gains the ability to cross
     * a path and leave no path behind while the potion is in effect.
     *
     * @params n/a.
     * @return n/a.
     * **/
    @Override
    public void consume() {
        if (getAmount() == 0) return;

        setAmount(getAmount() - 1);
        this.owner.setLeavesPath(false);
        this.owner.setShield(true);
    }

    // These are only used for testing.
    /**
     * getAmountTest() returns the amount that the Jump object is holding.
     * This method is only used for testing.
     *
     * @params n/a.
     * @return amount.
     * */
    public int getAmountTest() {
        return this.amount;
    }

    /**
     * getOwnerTest() returns the owner of the Jump object.
     * This method is only used for testing.
     *
     * @params n/a.
     * @return bike.
     * */
    public LightCycle getOwnerTest() {
        return this.owner;
    }

}
