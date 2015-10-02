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

//Importing the right packages
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.SplitPane;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GameManager is the class that handles the 2 player game mode of CarlTron.
 * Since the class handles the 2 player game mode it is responsible for finding
 * out whether the two LightCycles crashed, whether a LightCycle went off the
 * map, and the general movement of the LightCycles.
 * Methods include setUpAnimationTimer(), updateAnimation(), handle(KeyEvent),
 * onPauseButton(ActionEvent), setStage(Stage), getStage(), callVictoryPage().
 *
 * @params n/a.
 * @return n/a.
 * */
public class GameManager implements EventHandler<KeyEvent> {
    public static final double FRAMES_PER_SECOND = 30.0;
    public static final double STEP_SIZE = 5.0;
    private Grid grid = new Grid();
    public Stage primaryStage;
    private Player player1_object;
    private Player player2_object;
    public int win;
    public int p1s;
    public int p2s;
    public int numberPlayers;
    public int p1T;
    public int p2T;
    public int p1J;
    public int p2J;
    public GameManager current;

    //referencing the FXML file that is controlled by game controller.
    @FXML private Button pauseButton;
    @FXML private LightCycle player1;
    @FXML private LightCycle player2;
    @FXML private AnchorPane grid_fxml;
    @FXML private Label player1TurboLabel;
    @FXML private Label player2TurboLabel;
    @FXML private Label player1JumpLabel;
    @FXML private Label player2JumpLabel;
    @FXML private Label player1ScoreLabel;
    @FXML private Label player2ScoreLabel;

    @FXML private SplitPane game_stats_pane1;
    @FXML private SplitPane game_stats_pane2;
    @FXML private SplitPane game_stats_pane3;
    @FXML private SplitPane game_stats_pane4;

    //some more variables
    private boolean paused;
    private Timer timer;

    /**
     * GameManager() is the controller that handles the general 2 player
     * gameplay.
     * Methods include setUpAnimationTimer(), updateAnimation(),
     * handle(KeyEvent), onPauseButton(ActionEvent), setStage(Stage),
     * getStage(), callVictoryPage().
     *
     * @params n/.
     * @return n/a.
     * */
    public GameManager() {
        this.paused = false;
        this.primaryStage = null;
        this.win =0;
        this.p2T=3;
        this.p1T =3;
        this.p2J = 3;
        this.p1J  = 3;
        //Score gamescores = new Score(0,0);
    }

    /**
     * @method initialize() simply calls setUpAnimationTimer(). This function
     * is only
     * called when the controller is gotten.
     *
     * @params n/a.
     * @return n/a.
     * */
    public void initialize() throws Exception{
        this.setUpAnimationTimer();
    }

    /**
     * @method  that sets the score of the players to the right score.
     * @param p1
     * @param p2
     */
    public void setScore(int p1, int p2){
        this.p1s = p1;
        this.p2s = p2;
    }

    /**
     * @method that sets the right number of players when the user chooses
     * the player mode.
     * @param number
     */
    public void setPlayerNumber(int number){
        this.numberPlayers = number;
    }

    /**
     * @method that sets the current game manager object for reference's sake.
     * @param current
     */
    public void setGameManager(GameManager current){
        this.current = current;
    }

    /**
     * setUpAnimationTimer() sets up a timer and will keep track of when we
     * want to update our animation depending on the number of frames per
     * second specified.
     *
     * @params n/a.
     * @return n/a.
     * */
    public void setUpAnimationTimer(){
        TimerTask timerTask = new TimerTask() {
            public void run(){
                Platform.runLater(new Runnable() {
                    public void run(){
                        try {
                            if (updateAnimation()){
                                //updateAnimation();
                            }else{
                                updateAnimation();
                                callVictoryPage();
                                updateAnimation();
                            }
                        }catch (Exception e){
                            System.out.println("Error in updateAnimation()");
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }
        };

        //set the timer and everything
        final long startTimeInMilliseconds = 0;
        final long repetitionPeriodInMilliseconds = 100;
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     * updateAnimation() handles all the movement of the players and checking
     * for crashes, both with walls and with other the player's LightCycle.
     * Checks also whether a turbo is active and manages the movement of the
     * respective LightCycle accordingly. The same applies to jump (soon).
     *
     * @params n/a.
     * @return true or false.
     * */
    public boolean updateAnimation() throws Exception {
        this.game_stats_pane1.setDividerPositions(0.215f);
        this.game_stats_pane2.setDividerPositions(0.5f);
        this.game_stats_pane3.setDividerPositions(0.5f);
        this.game_stats_pane4.setDividerPositions(0.5f);
        this.player1ScoreLabel.setText(Integer.toString(this.p1s));
        this.player2ScoreLabel.setText(Integer.toString(this.p2s));
        this.player1TurboLabel.setText(Integer.toString(this.p1T));
        this.player2TurboLabel.setText(Integer.toString(this.p2T));
        this.player1JumpLabel.setText(Integer.toString(this.p1J));
        this.player2JumpLabel.setText(Integer.toString(this.p2J));

        if (this.player1_object == null && this.player2_object == null) {
            if (this.numberPlayers == 2) {
                this.player1_object = new Player(this.player1);
                this.player2_object = new Player(this.player2);
            } else if (this.numberPlayers == 1) {
                this.player1_object = new Player(this.player1);
                this.player2_object = new AiPlayer(this.player2,
                                            this.player1_object,
                                            this.grid);
                //this.player2_object.getVehicle().setLeavesPath(false);
            }
        }

        // reset the stat for whether a player can move or not.
        this.player1.moved = 0;
        this.player2.moved = 0;


        // find position of player1
        double player1X = this.player1.getLayoutX();
        double player1Y = this.player1.getLayoutY();

        // find position of player2
        double player2X = this.player2.getLayoutX();
        double player2Y = this.player2.getLayoutY();


        // find new position of player1
        double player1X_new = player1X + (STEP_SIZE * this.player1
                .getVelocityX());
        double player1Y_new = player1Y + (STEP_SIZE * this.player1
                .getVelocityY());

        // find new position of player2
        double player2X_new = player2X + (STEP_SIZE * this.player2
                .getVelocityX());
        double player2Y_new = player2Y + (STEP_SIZE * this.player2
                .getVelocityY());

        // do checks:
        ////////////// putting this before checking for wall collision

        // check if bike collided with a path.
        boolean path_check_p1 = this.grid.collisionWithPath(this.player1);
        boolean path_check_p2 = this.grid.collisionWithPath(this.player2);

        // player1 and player2 crashed at the same time --> draw (tie).
        if ((path_check_p1 == true) && (path_check_p2 == true)) {
            // crash
            this.win = 0;
            this.timer.cancel();
            return false;

            // player1 crashed --> player2 wins.
        } else if (path_check_p1 == true) {
            // crash
            this.win = 2;
            this.timer.cancel();
            return false;

            // player2 crashed --> player1 wins.
        } else if (path_check_p2 == true) {
            // crash
            this.timer.cancel();
            this.win = 1;
            return false;

        }

        boolean player1_off_grid = collisionWithWall(player1X_new,
                                                     player1Y_new);
        boolean player2_off_grid = collisionWithWall(player2X_new,
                                                     player2Y_new);

        // draw (tie), as both players went off the grid.
        if ((player1_off_grid == true) && (player2_off_grid == true)) {
            // draw
            this.win = 0;
            this.timer.cancel();
            return false;
        // player1 wins as player2 went off the grid.
        } else if (player2_off_grid == true) {
            // player1 wins.
            this.win = 1;
            this.timer.cancel();
            return false;
        // player2 wins as player1 went off the grid.
        } else if (player1_off_grid == true) {
            // player2 wins.
            this.win = 2;
            this.timer.cancel();
            return false;
        }


        // collision is only when their position overlap.
        // due to be moving objects they will not necessarily step on the same
        // square when they collide, so we need to check if they are colliding
        // taking into account where they were before the newest move.
        // p1--><--p2
        if ((player1X_new == player2X_new) && (player1Y_new == player2Y_new)) {
            this.win = 0;
            this.timer.cancel();
            return false;


            // p1-->p2
        } else if ((player1X_new == player2X) &&
                   (player1Y_new == player2Y_new)) {
            this.win = 0;//2;
            this.timer.cancel();
            return false;

            // p1<--p2
        } else if ((player1X == player2X_new) &&
                   (player1Y_new == player2Y_new)) {
            this.win = 0;//1;
            this.timer.cancel();
            return false;

            // p1/p2
        } else if ((player1X_new == player2X_new) &&
                   (player1Y_new == player2Y)) {
            this.win = 0;//2;
            this.timer.cancel();
            return false;

            // p2/p1
        } else if ((player1X_new == player2X_new) &&
                   (player1Y == player2Y_new)) {
            this.win = 0;//1;
            this.timer.cancel();
            return false;

        }

        //****** CHECK TURBO *******//
        this.player1_object.statusCheck();
        this.player2_object.statusCheck();

        // add new path rectangle to both the screen and the grid.
        GridCell path_p1 = this.grid.getCells()[(int)player1X][(int)player1Y];
        path_p1.setFill(Color.ORANGE);
        GridCell path_p2 = this.grid.getCells()[(int)player2X][(int)player2Y];
        path_p2.setFill(Color.WHITE);

        // only add the path if the player currently is leaving a path.
        // basically this is checking for whether the player has jump
        // activated or not.
        if (this.player1.hasPath()) {
            if (!grid_fxml.getChildren().contains(path_p1)) {
                this.grid_fxml.getChildren().add(path_p1);
                this.grid.addToPaths(path_p1);
            }
        }
        if (this.player2.hasPath()) {
            if (!grid_fxml.getChildren().contains(path_p2)) {
                this.grid_fxml.getChildren().add(path_p2);
                this.grid.addToPaths(path_p2);
            }
        }

        /********* AI HERE *******************/
        if (this.numberPlayers == 1) {
            for (int i=0; i<30; i++) {
                this.player2_object.strategy();
            }
            player2.moved = 1;
        }


        // NOTE:
        // need to add more path to the grid if turbo is on (as now it skips
        // one square because of the increase in speed).


        // update the position of player1 and player2, as they passed all the
        // collision tests.
        this.player1.step();
        this.player2.step();

        return true;
    }

    /**
     * collisionWithWall(double, double) takes a set of coordinates and
     * checks whether they are on the grid or not. Returns true if on the grid
     * and false if not on the grid.
     *
     * @params double, double
     * @return boolean
     * */
    public boolean collisionWithWall(double cordX, double cordY) {
        if (cordX < 0) {
            return true;
        } else if (cordY < 0) {
            return true;
        } else if (cordX + STEP_SIZE > this.grid_fxml.getWidth()) {
            return true;
        } else if (cordY + STEP_SIZE > this.grid_fxml.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * handle(KeyEvent) decides what to do with the different keys that are
     * pressed. Certain keys are used to control the two different players and
     * handle() handles that correctly.
     *
     * @params KeyEvent
     * @return n/a.
     * */
    @Override
    public void handle(KeyEvent keyEvent) {
        // only gets called when a key is pressed, so here we really only want
        // to update the velocity and direction in which the player is moving.

        KeyCode code = keyEvent.getCode();

        // player 2
        /* speed is the magnitude of player's velocity. Since velocityX is
        either 0 or positive/minus speed, we use the ternary operator to
        obtain the speed. */
        int speed1 = (this.player1.getVelocityX() == 0) ?
                         Math.abs(this.player1.getVelocityY()):
                         Math.abs(this.player1.getVelocityX());
        int speed2 = (this.player2.getVelocityX() == 0) ?
                         Math.abs(this.player2.getVelocityY()):
                         Math.abs(this.player2.getVelocityX());


        // the controls for player 2 are the arrow keys, enter and shift.
        // the controls for player 1 are w,a,s,d,q,e.
        // need to change the velocity of the different players depending
        // on which key was pressed.
        if (this.player2.moved == 0) {
            if (this.numberPlayers == 2) {
                if (code == KeyCode.LEFT) {
                    if (this.player2.getVelocityX() <= 0) {
                        // velocityX to -1 and Y to 0.
                        this.player2.setVelocityY(0);
                        this.player2.setVelocityX(-speed2);
                        this.player2.moved = 1;
                    }
                } else if (code == KeyCode.RIGHT) {
                    if (this.player2.getVelocityX() >= 0) {
                        // velocityX to 1 and Y to 0.
                        this.player2.setVelocityY(0);
                        this.player2.setVelocityX(speed2);
                        this.player2.moved = 1;
                    }
                } else if (code == KeyCode.UP) {
                    if (this.player2.getVelocityY() <= 0) {
                        // velocityX to 0 and Y to 1.
                        this.player2.setVelocityY(-speed2);
                        this.player2.setVelocityX(0);
                        this.player2.moved = 1;
                    }
                } else if (code == KeyCode.DOWN) {
                    if (this.player2.getVelocityY() >= 0) {
                        // velocityX to 0 and Y to -1.
                        this.player2.setVelocityY(speed2);
                        this.player2.setVelocityX(0);
                        this.player2.moved = 1;
                    }
                    // turbo 2nd player?
                } else if (code == KeyCode.ENTER) {
                    //this.player2.consume("turbo");
                    // jump 2nd player?
                    if (this.p2T >= 1) {
                        this.player2_object.consume("turbo");
                        this.p2T = this.p2T - 1;
                    }
                } else if (code == KeyCode.SHIFT) {

                    if (this.p2J >= 1) {
                        this.player2_object.consume("jump");
                        this.p2J = this.p2J - 1;
                    }
                }
            }
        }

        if (this.player1.moved == 0) {
            // player 1
            if (code == KeyCode.A) {
                if (this.player1.getVelocityX() <= 0) {
                    // velocityX to -1 and Y to 0.
                    this.player1.setVelocityY(0);
                    this.player1.setVelocityX(-speed1);
                    this.player1.moved = 1;
                }
            } else if (code == KeyCode.D) {
                if (this.player1.getVelocityX() >= 0) {
                    // velocityX to 1 and Y to 0.
                    this.player1.setVelocityY(0);
                    this.player1.setVelocityX(speed1);
                    this.player1.moved = 1;
                }
            } else if (code == KeyCode.W) {
                if (this.player1.getVelocityY() <= 0) {
                    // velocityX to 0 and Y to 1.
                    this.player1.setVelocityY(-speed1);
                    this.player1.setVelocityX(0);
                    this.player1.moved = 1;
                }
            } else if (code == KeyCode.S) {
                if (this.player1.getVelocityY() >= 0) {
                    // velocityX to 0 and Y to -1.
                    this.player1.setVelocityY(speed1);
                    this.player1.setVelocityX(0);
                    this.player1.moved = 1;
                }
                // turbo 1st player?
            } else if (code == KeyCode.Q) {

                if (this.p1T >= 1) {
                    this.player1_object.consume("turbo");
                    this.p1T = this.p1T - 1;
                }
                // jump 1st player?
            } else if (code == KeyCode.E) {

                if (this.p1J >= 1) {
                    this.player1_object.consume("jump");
                    this.p1J = this.p1J - 1;
                }
            }
        }

        // if no critical key is pressed, we do not need to do anything to the
        // velocity and direction of the LightCycles.
        keyEvent.consume();
    }

    /**
     * onPauseButton is a method that stops the animation by stopping the
     * calls to setUpAnimationTimer() when the game is to be paused, and starts
     * the animation again by calling setUpAnimationTimer() when the game is to
     * be resumed.
     *
     * @params ActionEvent.
     * @return n/a.
     * */
    public void onPauseButton(ActionEvent actionEvent) throws Exception {
        // depending on the state of the game (pause or in action) we want to
        // pause or resume it.
        if (this.paused) {
            this.setUpAnimationTimer();
            this.pauseButton.setText("Pause");
        } else {
            this.timer.cancel();
            this.pauseButton.setText("Resume");
        }
        this.paused = !this.paused;
    }

    /**
     * setStage(Stage) is a function used to forward the stage when loading new
     * scenes. It takes a stage and sets the primaryStage variable of the
     * GameManager controller to be that stage.
     *
     * @params Stage.
     * @return n/a.
     * */
    //this sets and saves the primary stage for reuse in the window recreation.
    public void setStage(Stage primary) {
        this.primaryStage = primary;
    }

    /**
     * getStage() simply return the stage that the current scene is in.
     *
     * @params n/a.
     * @return n/a.
     * */
    public Stage getStage(){
        return this.primaryStage;
    }

    /**
     * callVictoryPage() gives access to changing the scene of the stage to
     * the victory page by setting up a new WindowNavigation object that
     * is instructed to set up that page.
     *
     * @params n/a.
     * @return n/a.
     * */
    public void callVictoryPage() throws Exception{
        //System.out.println(this.win);
        WindowNavigation victor = new WindowNavigation();
        victor.setScore(this.p1s , this.p2s);
        victor.setpnumber(this.numberPlayers);
        victor.victorPage(this.primaryStage, this.win);
    }
}
