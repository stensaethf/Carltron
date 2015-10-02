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

//Importing all the neccessary libraries
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import javafx.util.StringConverter;


/**
 * @Class (Main.java) that is responsible of starting the game and loading
 * the first fxml file
 * @Params none
 */
public class Main extends Application {

    Scene scene1;
    Stage theStage;

    /**
     * The constructor for main. Takes no parameters and sets up nothing.
     *
     * @params nothing.
     * @return nothing.
     * */
    public Main() {
        //does nothing here
    }

    /**
     * @method start(Stage) is responsible for loading our menu scene (the
     * first
     * scene
     * to be displayed to the user).
     *
     * @return nothing.
     * @params nothing.
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // make sure that if the window is closed the appliation is also
        // closed.
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        this.theStage = primaryStage;

        // load the fxml file and create a scene with it.
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("carltron-menu.fxml"));

        Parent root = (Parent)loader.load();
        this.scene1 = new Scene(root, 800, 600);
        //play background music
        WindowNavigation backgroundMusic = new WindowNavigation();
        backgroundMusic.playBackgroundMusic();
        //creating a new window navigation object and setting the primary
        WindowNavigation new_window = loader.getController();
        new_window.setStage(primaryStage);

        //this sets the logo of the game to the custom logo
        primaryStage.getIcons().add(new Image(getClass().getResource
                ("images/logo.png").toExternalForm()));


        // put the scene into the stage and show it.
        primaryStage.setTitle("CarlTron");
        primaryStage.setScene(this.scene1);
        primaryStage.setResizable(false);
        setStage(primaryStage);
        primaryStage.show();

    }

    /**
     * @method setStage(Stage) takes a Stage and sets the variable theStage to
     * be that
     * stage. This is used for forwarding purposes, so that we can alter the
     * same stage later on.
     *
     * */
    public void setStage(Stage primary) {
        this.theStage = primary;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
