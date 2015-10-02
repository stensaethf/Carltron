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

//Neccessary imports
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Grid.
 */
public class GridTest {

    @Test
    public void testAddToGridWithNoneShouldStillHaveEmptyList() throws
            Exception {
        Grid grid = new Grid();
        grid.addToPaths(null);
        List<GridCell> grid_list = grid.getPaths();
        assertEquals(0, grid_list.size());
    }

    @Test
    public void testAddToGridWithRectangleShouldHaveListWithRectangle() throws
            Exception {
        Grid grid = new Grid();
        grid.addToPaths(grid.getCells()[100][100]);

        List<GridCell> grid_list = grid.getPaths();
        assertEquals(1, grid_list.size());
        assertEquals(100.0, grid_list.get(0).getLayoutX(), 0.1);
        assertEquals(100.0, grid_list.get(0).getLayoutY(), 0.1);
    }

    @Test
    public void testCollisionWithPathWithNoneShouldReturnFalse() throws
            Exception {
        Grid grid = new Grid();
        assertEquals(false, grid.collisionWithPath(null));
    }

    @Test
    public void testCollisionWithPathWithRectangleShouldReturnFalse() throws
            Exception {
        Grid grid = new Grid();

        LightCycle bike = new LightCycle();
        bike.setLayoutX(10);
        bike.setLayoutY(10);

        assertEquals(false, grid.collisionWithPath(bike));
    }

    @Test
    public void
    testCollisionWithPathWithRectangleOnNoneEmptyListShouldReturnFalse() throws
            Exception {
        Grid grid = new Grid();

        grid.addToPaths(grid.getCells()[100][100]);

        LightCycle bike = new LightCycle();
        bike.setLayoutX(10);
        bike.setLayoutY(10);

        assertEquals(false, grid.collisionWithPath(bike));
    }

    @Test
    public void testCollisionWithPathWithRectangleWithItemShouldReturnTrue()
            throws
            Exception {
        Grid grid = new Grid();

        grid.addToPaths(grid.getCells()[100][100]);

        LightCycle bike = new LightCycle();
        bike.setLayoutX(100);
        bike.setLayoutY(100);

        assertEquals(true, grid.collisionWithPath(bike));
    }

    @Test
    public void testHasWayToPathWithSamePointShouldReturnTrue() throws
            Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(0.0, 0.0, 0.0, 0.0);

        assertEquals(true, result);
    }

    @Test
    public void testHasWayToPathWithDifferentPointShouldReturnTrue() throws
            Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(0.0, 0.0, 5.0, 5.0);

        assertEquals(true, result);
    }

    @Test
    public void testHasWayToPathWithNegativeBeginningShouldReturnFalse() throws
            Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(-5.0, -5.0, 0.0, 0.0);

        assertEquals(false, result);
    }

    @Test
    public void testHasWayToPathWithNegativeEndShouldReturnFalse() throws
            Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(0.0, 0.0, -5.0, -5.0);

        assertEquals(false, result);
    }

    @Test
    public void testHasWayToPathWithNonMultipleOf5StartShouldReturnFalse()
            throws Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(1.0, 1.0, 5.0, 5.0);

        assertEquals(false, result);
    }

    @Test
    public void testHasWayToPathWithNonMultipleOf5EndShouldReturnFalse()
            throws Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(0.0, 0.0, 3.0, 3.0);

        assertEquals(false, result);
    }

    @Test
    public void testHasWayToPathWithTooLargeStartShouldReturnFalse()
            throws Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(1000.0, 1000.0, 5.0, 5.0);

        assertEquals(false, result);
    }

    @Test
    public void testHasWayToPathWithTooLargeEndShouldReturnFalse()
            throws Exception {
        Grid grid = new Grid();

        boolean result = grid.hasWayToPoint(0.0, 0.0, 1000.0, 1000.0);

        assertEquals(false, result);
    }
}