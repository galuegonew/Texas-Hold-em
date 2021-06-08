package menu;

import menu.scenes.ScenePlay;
import menu.scenes.SceneOption;
import menu.scenes.SceneHelp;
import menu.scenes.SceneCredit;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainMenu extends Application {

    // Variables
    private static Scene scene;
    private static VBox menuBox;
    private static VBox title;
    private static int currentItem = 0;

    // Constants
    private final int SCENE_WIDTH = 900;
    private final int SCENE_HEIGHT = 600;
    private final String BACKGROUND_IMG_PATH = "images/bck.jpg";
    private final int VBOX_SPACING = 10;

    /**
     * Creates the main menu with a pane, menu items, scenes and handlers for each
     * 
     * @param primaryStage The stage that is used for the main menu
     * @return returns the pane containing the main menu
     */
    private Pane createMainMenu(Stage primaryStage) {

        // Main pane
        Pane root = new Pane();

        // Menu items
        MenuItem itemPlay = new MenuItem("PLAY", 20);
        MenuItem itemOption = new MenuItem("OPTIONS", 20);
        MenuItem itemHelp = new MenuItem("HELP", 20);
        MenuItem itemCredit = new MenuItem("CREDITS", 20);
        MenuItem itemExit = new MenuItem("EXIT", 20);

        // Scenes
        ScenePlay scenePlay = new ScenePlay();
        //SceneOption sceneOption = new SceneOption();
        SceneHelp sceneHelp = new SceneHelp();
        SceneCredit sceneCredit = new SceneCredit();

        // Event handlers
        itemPlay.setOnActivate(() -> {
            primaryStage.setScene(ScenePlay.getScene());
            scenePlay.setStage(primaryStage);
        });

        // Implement them first
        /*
         * itemOption.setOnActivate(() -> {
         * primaryStage.setScene(sceneOption.getScene());
         * sceneOption.setStage(primaryStage); });
         */

        itemHelp.setOnActivate(() -> {
            primaryStage.setScene(SceneHelp.getScene());
            sceneHelp.setStage(primaryStage);
        });

        itemCredit.setOnActivate(() -> {
            primaryStage.setScene(SceneCredit.getScene());
            sceneCredit.setStage(primaryStage);
        });

        itemExit.setOnActivate(() -> System.exit(0));

        // Add it up together
        menuBox = new VBox(VBOX_SPACING, itemPlay, itemOption, itemHelp, itemCredit, itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(360);
        menuBox.setTranslateY(200);

        title = new VBox(VBOX_SPACING, new MenuItem("CSCI2020U PROJECT", 22), new MenuItem("TEXAS HOLD-EM", 22));
        title.setAlignment(Pos.TOP_CENTER);
        title.setTranslateX(300);
        title.setTranslateY(100);

        root.getChildren().addAll(title, menuBox);

        // Background
        File imgF = new File(BACKGROUND_IMG_PATH);
        root.setStyle("-fx-background-image: url(" + imgF.toURI().toString() + "); -fx-background-size: cover;");

        return root;
    }

    /**
     * Returns the scene that is currently used
     * 
     * @return the scene that is currently used
     */
    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        scene = new Scene(createMainMenu(primaryStage), SCENE_WIDTH, SCENE_HEIGHT);

        // Creating navigation for scene
        MenuNav nav = new MenuNav(scene, menuBox, currentItem);
        nav.getMenuItem(0).setActive(true);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}