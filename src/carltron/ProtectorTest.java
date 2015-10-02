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
 * Tests for Protector.
 */
public class ProtectorTest {

    @Test
    public void testConstructorWith2NullShouldReturn2AndNull() throws
            Exception {
        Protector protector = new Protector(2, null);
        assertEquals(2, protector.getAmountTest());
        assertEquals(null, protector.getOwnerTest());
    }

    @Test
    public void testConstructorWithhNegative1BikeShouldReturn0AndTheBike()
            throws
            Exception {
        LightCycle bike = new LightCycle();
        Protector protector = new Protector(-1, bike);
        assertEquals(bike, protector.getOwnerTest());
        assertEquals(0, protector.getAmountTest());
    }

    @Test
    public void testConstructorWithh6BikeShouldReturn6AndTheBike()
            throws
            Exception {
        LightCycle bike = new LightCycle();
        Protector protector = new Protector(6, bike);
        assertEquals(bike, protector.getOwnerTest());
        assertEquals(6, protector.getAmountTest());
    }

    @Test
    public void testConsumeWith1TurboShouldReturn0() throws Exception {
        LightCycle bike = new LightCycle();
        Protector protector = new Protector(1, bike);
        protector.consume();
        assertEquals(0, protector.getAmountTest());
    }

    @Test
    public void testConsumeWith0TurboShouldReturn0() throws Exception {
        LightCycle bike = new LightCycle();
        Protector protector = new Protector(0, bike);
        protector.consume();
        assertEquals(0, protector.getAmountTest());
    }
}