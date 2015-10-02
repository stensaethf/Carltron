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

//Importing all important libraries
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Grid.java
 * This class will (1) record the path of LightCycles
 * (2) detect collision between LightCycles and the paths.
 * June 1, 2015 - version 1
 */
public class Grid {
    //an arraylist of path
    private GridCell[][] cells;
    private List<GridCell> paths = new ArrayList<>();
    private Queue<GridCell> queue;

    public static final double DEFAULT_WIDTH = 590;
    public static final double DEFAULT_HEIGHT = 460;

    /**
     * The constructor for Grid. Takes no parameters and sets up nothing.
     * */
    public Grid() {
        cells = new GridCell[(int)DEFAULT_WIDTH + 1][(int)DEFAULT_HEIGHT + 1];
        for (int i = 0; i <= (int)DEFAULT_WIDTH; i += 5) {
            for (int j = 0; j <= (int)DEFAULT_HEIGHT; j += 5) {
                cells[i][j] = new GridCell(i, j, GameManager.STEP_SIZE,
                                           GameManager.STEP_SIZE);
            }
        }
        // set the neighbors of all cells
        for (int i = 0; i <= (int)DEFAULT_WIDTH; i += 5) {
            for (int j = 0; j <= (int)DEFAULT_HEIGHT; j += 5) {
                if (i <= DEFAULT_WIDTH - 5) {
                    cells[i][j].setNeighbors(cells[i + 5][j]);
                }
                if (i >= 5) {
                    cells[i][j].setNeighbors(cells[i - 5][j]);
                }
                if (j <= DEFAULT_HEIGHT - 5) {
                    cells[i][j].setNeighbors(cells[i][j + 5]);
                }
                if (j >= 5) {
                    cells[i][j].setNeighbors(cells[i][j - 5]);
                }
            }
        }
        this.queue = new ArrayBlockingQueue<>((int)(Math.ceil(this
                .DEFAULT_HEIGHT)*Math.ceil(this.DEFAULT_WIDTH)));
    }

    public GridCell[][] getCells() {
        return this.cells;
    }

    /**
     * addToGrid(Rectangle) adds a rectangle to into the arrayList, which
     * enables us to update the path the LightCycles leave behind them.
     *
     * @params path
     */
    public void addToPaths(GridCell cell) {
        if (cell != null) {
            if (!cell.isWall()) {
                cell.setWall(true);
                paths.add(cell);
            }
        }
    }

    /**
     * getGridList() returns the arraylist of path so we can draw.
     * This method is used for testing.
     *
     * @return ArrayList<Rectangle>
     */
    public List<GridCell> getPaths() {
        return this.paths;
    }

    /**
     * collisionWithPath(LightCycle) detects collision of bike and path
     *
     * @param bike
     * @return true or false
     */
    public boolean collisionWithPath(LightCycle bike) {
        if (bike != null) {
            //if bike doesn't have protector
            if (!bike.hasShield()) {
                // loop over rectangles in path and check whether
                // layout is the same as the bike's layout.
                for (GridCell path : paths) {
                    if ((bike.getLayoutX() == path.getLayoutX()) &&
                            (bike.getLayoutY() == path.getLayoutY())) {
                        return true;
                    }
                }
            }
            // return false if no collision
            return false;
        }
        return false;
    }

    public boolean hasWayToPoint(double currentX, double currentY,
                                  double endX, double endY) {
        if ((currentX < 0.0) || (currentY < 0.0) || (endX < 0.0) || (endY <
                0.0)) {
            return false;
        } else if ((currentX % 5 != 0) || (currentY % 5 != 0) ||
                (endX % 5 != 0) || (endY % 5 != 0)) {
            return false;
        } else if ((currentX > 590) || (currentY > 460)) {
            return false;
        }


        List<GridCell> seen = new ArrayList<>();
        GridCell start = this.cells[(int) currentX][(int) currentY];
        this.queue.add(start);
        seen.add(start);

        while (!this.queue.isEmpty()) {
            GridCell current = this.queue.poll();

            for (GridCell neighbor : current.getNeighbors()) {
                if (!neighbor.isWall()) {
                    if (neighbor.getLayoutX() == endX &&
                            neighbor.getLayoutY() == endY) {
                        return true;
                    }
                    if (!seen.contains(neighbor)) {
                        seen.add(neighbor);
                        this.queue.add(neighbor);
                    }
                }
            }
        }
        this.queue.clear();
        return false;
    }

    //********** FOR TESTING ONLY **************//
    public void printPaths() {
        for (GridCell path : paths) {
            System.out.print("("+path.getLayoutX()+", "+path.getLayoutY()+") ");
        }
    }
}



