package menu.scenes;

import menu.MenuItem;
import menu.MainMenu;
import user.Profile;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;

public class SceneOption {
    /*
    Moiz Abdul | March 7th 2019

    Mostly done Options menu
    Need confirm button that writes to file saving persons choice
    Back menuItem doesnt work
    Maybe add more cards?
    Higher res cards?

    */
        // Variables
        private static Scene scene;
        private static Stage s;
        private VBox menuBox;
        private VBox menuBox1;
        private VBox menuBox2;
        private VBox menuBox3;
        private VBox menuBox4;
        private VBox menuBox5;
        Rectangle bg;

        private static int currentItem = 0;

        // Constants
        private final int SCENE_WIDTH = 900;
        private final int SCENE_HEIGHT = 600;
        private final int VBOX_SPACING = 10;
        private final String BACKGROUND_IMG_PATH = "images/bck.jpg";

        public SceneOption() {

            Pane pane = new Pane();
            pane.setPrefSize(900, 600);
            bg = new Rectangle(SCENE_WIDTH,SCENE_HEIGHT);
            bg.setStroke(Color.BLACK);
            CardImage c = new CardImage();
            ImageView Card = new ImageView();

            MenuItem back = new MenuItem("BACK",14);
            menuBox2 = new VBox(10, back);
            menuBox2.setTranslateX(30);
            menuBox2.setTranslateY(530);
            back.setOnMouseClicked(e -> s.setScene(MainMenu.getScene()));
            //Grouped radio buttons for selecting card back
            // Change imageview of card and change card value in cardback class
            RadioButton opt1 = new RadioButton("Style 1");
            opt1.setFont(Font.font("Berlin Sans FB", 14));
            opt1.setStyle("-fx-text-fill: white");
            opt1.setOnMousePressed(e -> {
                c.setImg("../images/c1.png");
                Image img = new Image("file:"+c.getImg(), 72, 96, false, false);
                Card.setImage(img);
            });
            opt1.setLayoutX(450);
            opt1.setLayoutY(200);
            RadioButton opt2 = new RadioButton("Style 2");
            opt2.setFont(Font.font("Berlin Sans FB", 14));
            opt2.setStyle("-fx-text-fill: white");
            opt2.setOnAction(e -> {
                c.setImg("../images/c2.jpg");
                Image img = new Image("file:"+c.getImg(), 72, 96, false, false);
                Card.setImage(img);
            });
            opt2.setLayoutX(450);
            opt2.setLayoutY(225);
            RadioButton opt3 = new RadioButton("Style 3");
            opt3.setFont(Font.font("Berlin Sans FB", 14));
            opt3.setStyle("-fx-text-fill: white");
            opt3.setOnMouseClicked(e -> {
                c.setImg("../images/c3.png");
                Image img = new Image("file:"+c.getImg(), 72, 96, false, false);
                Card.setImage(img);
            });
            opt3.setLayoutX(450);
            opt3.setLayoutY(250);
            RadioButton opt4 = new RadioButton("Style 4");
            opt4.setFont(Font.font("Berlin Sans FB", 14));
            opt4.setStyle("-fx-text-fill: white");
            opt4.setOnMouseClicked(e -> {
                c.setImg("../../images/c4.png");
                Image img = new Image("file:"+c.getImg(), 72, 96, false, false);
                Card.setImage(img);
            });
            opt4.setLayoutX(450);
            opt4.setLayoutY(275);
            ToggleGroup cardOpt = new ToggleGroup();
            opt1.setToggleGroup(cardOpt);
            opt2.setToggleGroup(cardOpt);
            opt3.setToggleGroup(cardOpt);
            opt4.setToggleGroup(cardOpt);

            Card.setLayoutX(375);
            Card.setLayoutY(200);

            Text head = new Text("Profile");
            head.setStyle("-fx-text-stroke: white; -fx-font-size: 26; -fx-underline: true");
            head.setLayoutX(375);
            head.setLayoutY(50);

            Profile p = new Profile();
            p.updateCareerStats(1100,109,15);

            Text chips = new Text("Money won in career: "+p.getCareerChips());
            chips.setStyle("-fx-text-stroke: white; -fx-font-size: 16");
            chips.setLayoutX(375);
            chips.setLayoutY(100);
            Text played = new Text("Hands played in career: "+p.getCareerHands());
            played.setStyle("-fx-text-stroke: white; -fx-font-size: 16");
            played.setLayoutX(375);
            played.setLayoutY(125);
            Text won = new Text("Hands won in career: "+p.getCareerHandsWon());
            won.setStyle("-fx-text-stroke: white; -fx-font-size: 16");
            won.setLayoutX(375);
            won.setLayoutY(150);

            pane.getChildren().addAll(opt1,opt2,opt3,opt4, Card, menuBox2, head, played, won, chips);
            scene = new Scene(pane, 900, 600);
            File imgF = new File(BACKGROUND_IMG_PATH);
            pane.setStyle("-fx-background-image: url(" + imgF.toURI().toString() + "); -fx-background-size: cover;");
        }
        private MenuItem getMenuItem(int index)
    {
        return (MenuItem)menuBox2.getChildren().get(index);
    }

        public Stage getStage() {return s; }

        public static Scene getScene () {
        return scene;
        }

        public void setStage (Stage primaryStage)
        {
            s = primaryStage;
        }

        class CardImage {
            private int x;
            private int y;
            private String img;
            public CardImage(){this.img="../images/backCard.png";}
            public void setImg(String location){
                this.img = location;
            }
            public String getImg(){
                System.out.println(this.img);
                return this.img;
            }
        }
}
