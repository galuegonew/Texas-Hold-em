package menu.scenes;

import menu.*;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SceneCredit {

    // Variables
    private static Scene scene;
    private static Stage stage;
    private static VBox menuBox;
    private static int currentItem = 0;

    // Constants
    private final int SCENE_WIDTH = 900;
    private final int SCENE_HEIGHT = 600;
    private final int VBOX_SPACING = 10;
    private final String BACKGROUND_IMG_PATH = "images/bck.jpg";

    /**
     * Constructor creates the credit scene using a pane, menu items, scenes and
     * handlers for each
     */
    public SceneCredit() {

        // Main pane
        Pane pane = new Pane();


        // Menu items
        MenuItem back = new MenuItem("BACK", 20);


        // Event Handlers
        back.setOnActivate(() -> stage.setScene(MainMenu.getScene()));


        // Add it up together
        menuBox = new VBox(VBOX_SPACING, new MenuItem("ALBERT GALUEGO", 20), new MenuItem("PETER NAGY", 20),
                new MenuItem("JEVON RAMBARRAN", 20), new MenuItem("MOIZ ABDUL", 20), back);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(320);
        menuBox.setTranslateY(180);

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