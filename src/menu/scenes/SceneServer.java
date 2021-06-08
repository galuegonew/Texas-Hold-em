package menu.scenes;

import menu.*;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SceneServer {

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

    /**
     * Constructor creates the play scene using a pane, menu items, scenes and
     * handlers for each
     */
    public SceneServer() {

        // Main pane
        Pane pane = new Pane();

        // Menu items
        MenuItem item2Players = new MenuItem("2 PLAYERS", 20);
        MenuItem item3Players = new MenuItem("3 PLAYERS", 20);
        MenuItem item4Players = new MenuItem("4 PLAYERS", 20);
        MenuItem back = new MenuItem("BACK", 20);
        
        // Event Handlers
        item2Players.setOnActivate(() -> {
            sceneLoadingScreen = new SceneLoadingScreen(1); // Create loading screen with number of clients to connect

            stage.setScene(SceneLoadingScreen.getScene());
            sceneLoadingScreen.setStage(stage);
        });

        item3Players.setOnActivate(() -> {
            sceneLoadingScreen = new SceneLoadingScreen(2); // Create loading screen with number of clients to connect

            stage.setScene(SceneLoadingScreen.getScene());
            sceneLoadingScreen.setStage(stage);
        });

        item4Players.setOnActivate(() -> {
            sceneLoadingScreen = new SceneLoadingScreen(3); // Create loading screen with number of clients to connect

            stage.setScene(SceneLoadingScreen.getScene());
            sceneLoadingScreen.setStage(stage);
        });

        back.setOnActivate(() -> stage.setScene(ScenePlay.getScene()));

        // Add it up together
        menuBox = new VBox(VBOX_SPACING, item2Players, item3Players, item4Players, back);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(340);
        menuBox.setTranslateY(200);

        pane.getChildren().add(menuBox);

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