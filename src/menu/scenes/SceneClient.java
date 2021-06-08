package menu.scenes;

import menu.*;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SceneClient {

    // Variables
    private static Scene scene;
    private static Stage stage;
    private static VBox menuBox;
    private static int currentItem = 0;

    // Scene
    private SceneLoadingScreen sceneLoadingScreen;

    // Constants
    private final int SCENE_WIDTH = 900;
    private final int SCENE_HEIGHT = 600;
    private final int VBOX_SPACING = 10;
    private final String BACKGROUND_IMG_PATH = "images/bck.jpg";
    private final int TIMEOUT = 1000; // 1 second

    /**
     * Constructor creates the play scene using a pane, menu items, scenes and
     * handlers for each
     */
    public SceneClient() {

        // Main pane
        Pane pane = new Pane();

        // Menu items
        MenuItem itemJoin = new MenuItem("JOIN", 20);
        MenuItem back = new MenuItem("BACK", 20);

        // TextField
        TextField tf = new TextField();

        // Event Handlers
        itemJoin.setOnActivate(() -> {

            String host = tf.getText();

            if (host != "") {

                try {
                    if (InetAddress.getByName(host).isReachable(TIMEOUT)) { // If host is reachable then connect to host
                        sceneLoadingScreen = new SceneLoadingScreen(host);

                        stage.setScene(SceneLoadingScreen.getScene());
                        sceneLoadingScreen.setStage(stage);
                    }
                    else{
                        tf.setStyle("-fx-border-color: red;");
                        System.err.println("Server is not reachable");
                    }
                }
                catch (UnknownHostException e) {

                    tf.setStyle("-fx-border-color: red;");
                    System.err.println("Unknown host exception");
                }
                catch (IOException e) {
                    
                    tf.setStyle("-fx-border-color: red;");
                    System.err.println("IO exception");
                }
            }
            else{
                tf.setStyle("-fx-border-color: red;");
                System.err.println("No input");
            }
        });

        back.setOnActivate(() -> stage.setScene(ScenePlay.getScene()));

        // Unfocus textfield when clicked in pane
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.requestFocus();
            }
        });
        

        // Add it up together
        menuBox = new VBox(VBOX_SPACING, itemJoin, back);

        VBox pack = new VBox(VBOX_SPACING, tf, menuBox);
        pack.setAlignment(Pos.CENTER);
        pack.setTranslateX(340);
        pack.setTranslateY(200);

        pane.getChildren().add(pack);

        // Background
        File imgF = new File(BACKGROUND_IMG_PATH);
        pane.setStyle("-fx-background-image: url(" + imgF.toURI().toString() + "); -fx-background-size: cover;");

        // Creating scene with pane
        scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);

        // Creating navigation for scene
        MenuNav nav = new MenuNav(scene, menuBox, currentItem);
        nav.getMenuItem(0).setActive(true);
    }

    /**
     * Sets the stage to a new stage
     * 
     * @param primaryStage new stage
     */
    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Returns the scene that is currently used
     * 
     * @return the scene that is currently used
     */
    public static Scene getScene() {
        return scene;
    }
}