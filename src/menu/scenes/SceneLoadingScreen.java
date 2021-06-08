package menu.scenes;

import menu.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import game.GameScene;
import javafx.scene.layout.Pane;
import server.Server;
import server.Client;

public class SceneLoadingScreen {

    // Variables
    private static Scene scene;
    private static Stage stage;

    // Constants
    private final int SCENE_WIDTH = 900;
    private final int SCENE_HEIGHT = 600;
    private final String BACKGROUND_IMG_PATH = "images/waitingBck.png";

    /**
     * Constructor for server
     */
    public SceneLoadingScreen(int clientNum) {

        // Main pane
        Pane pane = new Pane();

        Server server = new Server(clientNum, 2000);
        server.start();

        // Check if all players have connected in thread
        new Thread(() -> {

            while (true) {
                if (server.getclientCons() == clientNum) {

                    // Send players the client number so that they can connect to game scene
                    for (int i = 0; i < clientNum; i++) {
                        server.sendMsg(String.valueOf(clientNum), i);
                    }

                    GameScene gameScene = new GameScene(server, clientNum);
                    
                    Platform.runLater(() -> {
                        // Redirect window to game scene
                        stage.setScene(GameScene.getScene());
                        gameScene.setStage(stage);
                    });
                    break;
                }

                try {
                    Thread.sleep(1000);     // Reduce processor load
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Show server IP address
        MenuItem itemHost = new MenuItem("HOST: " + server.getHost(), 25);
        
        itemHost.setLayoutX(320);
        itemHost.setLayoutY(150);

        pane.getChildren().add(itemHost);

        // Background
        File imgF = new File(BACKGROUND_IMG_PATH);
        pane.setStyle("-fx-background-image: url(" + imgF.toURI().toString() + "); -fx-background-size: cover;");

        // Creating scene with pane
        scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
    }

    // Constructor for client
    public SceneLoadingScreen(String host) {

        // Main pane
        Pane pane = new Pane();

        Client client = new Client(host, 2000);
        client.start();

        new Thread(() -> {
 
            GameScene gameScene = new GameScene(client, Integer.valueOf(client.readMsg()));

            Platform.runLater(() -> {
                // Redirect window to game scene
                stage.setScene(GameScene.getScene());
                gameScene.setStage(stage);
            });
        }).start();

        // Background
        File imgF = new File(BACKGROUND_IMG_PATH);
        pane.setStyle("-fx-background-image: url(" + imgF.toURI().toString() + "); -fx-background-size: cover;");

        // Creating scene with pane
        scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
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