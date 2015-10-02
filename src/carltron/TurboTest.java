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
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Frederik on 01.06.15.
 * Tests for Turbo.
 */
public class TurboTest {

    @Test
    public void testConstructorWith2NullShouldReturn2AndNull() throws
            Exception {
        Turbo turbo = new Turbo(2, null);
        assertEquals(2, turbo.getAmountTest());
        assertEquals(null, turbo.getOwnerTest());
    }

    @Test
    public void testConstructorWithhNegative1BikeShouldReturn0AndTheBike()
            throws
            Exception {
        LightCycle bike = new LightCycle();
        Turbo turbo = new Turbo(-1, bike);
        assertEquals(bike, turbo.getOwnerTest());
        assertEquals(0, turbo.getAmountTest());
    }

    @Test
    public void testConstructorWithhNegative1BikeShouldReturn5AndTheBike()
            throws
            Exception {
        LightCycle bike = new LightCycle();
        Turbo turbo = new Turbo(6, bike);
        assertEquals(bike, turbo.getOwnerTest());
        assertEquals(6, turbo.getAmountTest());
    }

    @Test
    public void testConsumeWith1TurboShouldReturn2ForX() throws Exception {
        LightCycle bike = new LightCycle();
        bike.setVelocityX(1);
        bike.setVelocityY(0);
        Turbo turbo = new Turbo(1, bike);
        turbo.consume();
        assertEquals(0, turbo.getAmountTest());
        assertEquals(2, turbo.getOwnerTest().getVelocityX());
    }

    @Test
    public void testConsumeWith1TurboShouldReturn2ForY() throws Exception {
        LightCycle bike = new LightCycle();
        bike.setVelocityX(0);
        bike.setVelocityY(1);
        Turbo turbo = new Turbo(1, bike);
        turbo.consume();
        assertEquals(0, turbo.getAmountTest());
        assertEquals(2, turbo.getOwnerTest().getVelocityY());
    }

    @Test
    public void testConsumeWith0TurboShouldReturn1ForX() throws Exception {
        LightCycle bike = new LightCycle();
        bike.setVelocityX(1);
        bike.setVelocityY(0);
        Turbo turbo = new Turbo(0, bike);
        turbo.consume();
        assertEquals(0, turbo.getAmountTest());
        assertEquals(1, turbo.getOwnerTest().getVelocityX());
    }

    @Test
    public void testConsumeWith0TurboShouldReturn0ForY() throws Exception {
        LightCycle bike = new LightCycle();
        bike.setVelocityX(1);
        bike.setVelocityY(0);
        Turbo turbo = new Turbo(0, bike);
        turbo.consume();
        assertEquals(0, turbo.getAmountTest());
        assertEquals(0, turbo.getOwnerTest().getVelocityY());
    }
}