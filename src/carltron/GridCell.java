package carltron;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shangd on 6/7/15.
 */
public class GridCell extends Rectangle {

    private List<GridCell> neighbors;
    private boolean wall;

    public GridCell(double x, double y, double width, double height) {
        super();
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.wall = false;
        this.neighbors = new ArrayList<>();
    }

    public boolean isWall() {
        return this.wall;
    }

    public void setWall(boolean flag) {
        this.wall = flag;
    }

    public List<GridCell> getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(GridCell neighbor) {
        if (neighbor != null) {
            this.neighbors.add(neighbor);
        }
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof GridCell)) {
            return false;
        } else {
            return (this.getLayoutX() == ((GridCell) that).getLayoutX()) &&
                   (this.getLayoutY() == ((GridCell) that).getLayoutY());
        }
    }

    // Only for internal testing
    @Override
    public String toString() {
        int x = (int)this.getLayoutX();
        int y = (int)this.getLayoutY();
        return "("+x+", "+y+", "+this.isWall()+")";
    }
}
