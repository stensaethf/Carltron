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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Frederik on 01.06.15.
 * Tests for Jump.
 */
public class JumpTest {

    @Test
    public void testConstructorWith2NullShouldReturn2AndNull() throws
            Exception {
        Jump jump = new Jump(2, null);
        assertEquals(2, jump.getAmountTest());
        assertEquals(null, jump.getOwnerTest());
    }

    @Test
    public void testConstructorWithhNegative1BikeShouldReturn0AndTheBike()
            throws
            Exception {
        LightCycle bike = new LightCycle();
        Jump jump = new Jump(-1, bike);
        assertEquals(bike, jump.getOwnerTest());
        assertEquals(0, jump.getAmountTest());
    }

    @Test
    public void testConsumeWith1JumpShouldReturn0AndNoPath() throws Exception {
        LightCycle bike = new LightCycle();
        Jump jump = new Jump(1, bike);
        jump.consume();
        assertEquals(0, jump.getAmountTest());
        assertEquals(false, jump.getOwnerTest().hasPath());
    }

    @Test
    public void testConsumeWith0JumpShouldReturn0AndHasPath() throws Exception {
        LightCycle bike = new LightCycle();
        Jump jump = new Jump(0, bike);
        jump.consume();
        assertEquals(0, jump.getAmountTest());
        assertEquals(true, jump.getOwnerTest().hasPath());
    }
}