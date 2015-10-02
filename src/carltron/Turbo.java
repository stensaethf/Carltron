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
 * The Turbo class is responsible for keeping track of how many more turbos is
 * available to a LightCycle - the owner of the Turbo object.
 */
public class Turbo extends Bonus {
    public final static int BOOST_VELOCITY = 1;

    /**
     * The constructor for the Turbo class.
     * Takes an integer to represent the amount of turbos available (newAmount)
     * to the LightCycle and sets amount to be this number.
     *
     **/
    public Turbo(int amount, LightCycle owner) {
        super();
        if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }

        this.owner = owner;
    }

    /**
     * Consume() consumes a potion of turbo and gains the ability to move
     * twice as fast as before.
     *
     * @params n/a.
     * @return n/a.
     * **/
    @Override
    public void consume() {
        if (getAmount() == 0) {
            return;
        }

        if (this.owner.getVelocityX() == -1) {
            this.owner.setVelocityX(this.owner.getVelocityX() - BOOST_VELOCITY);
            setAmount(getAmount() - 1);
        } else if (this.owner.getVelocityX() == 1) {
            this.owner.setVelocityX(this.owner.getVelocityX() + BOOST_VELOCITY);
            setAmount(getAmount() - 1);
        } else if (this.owner.getVelocityY() == -1) {
            this.owner.setVelocityY(this.owner.getVelocityY() - BOOST_VELOCITY);
            setAmount(getAmount() - 1);
        } else if (this.owner.getVelocityY() == 1) {
            this.owner.setVelocityY(this.owner.getVelocityY() + BOOST_VELOCITY);
            setAmount(getAmount() - 1);
        }
    }

    // These are only used for testing.
    /**
     * getAmountTest() returns the amount that the Turbo object is holding.
     * This method is only used for testing.
     *
     * @params n/a.
     * @return amount.
     * */
    public int getAmountTest() {
        return this.amount;
    }

    /**
     * getOwnerTest() returns the owner of the Turbo object.
     * This method is only used for testing.
     *
     * @params n/a.
     * @return bike.
     * */
    public LightCycle getOwnerTest() {
        return this.owner;
    }
}
