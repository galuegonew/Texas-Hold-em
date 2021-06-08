package menu;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItem extends HBox {
    // Variables
    private ImageView chip1 = getPokerChip(), chip2 = getPokerChip();
    private Text text;
    private Runnable script;
    int fontSize2;

    // Constants
    private final String CHIP_IMG_PATH = "images/chip.png";
    private final int CHIP_SIZE = 15;

    /**
     * Constructor creates menu items and sets a handler for them
     * 
     * @param name     name of the menu item
     * @param fontSize font size of the menu item
     */
    public MenuItem(String name, int fontSize) {
        super(15);
        setAlignment(Pos.CENTER);

        text = new Text(name);
        text.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD, fontSize));
        fontSize2 = fontSize;

        getChildren().addAll(chip1, text, chip2);
        setActive(false);
        setOnActivate(() -> System.out.println(name + " activated")); // For unhandled menu items
    }
    
    public void disableBold()
    {
    	text.setFont(Font.font("Berlin Sans FB", fontSize2));
    }

    /**
     * Returns an imageview of a poker chip
     * 
     * @return imageview of poker chip
     */
    public ImageView getPokerChip() {
        File imgF = new File(CHIP_IMG_PATH);
        ImageView img = new ImageView(new Image(imgF.toURI().toString()));
        img.setFitWidth(CHIP_SIZE);
        img.setFitHeight(CHIP_SIZE);
        return img;
    }

    public void setText(String text){
        this.text.setText(text);
    }

    /**
     * Sets the item active or not
     * 
     * @param state the state to set the item
     */
    public void setActive(boolean state) {
        chip1.setVisible(state);
        chip2.setVisible(state);
        text.setFill(state ? Color.rgb(242, 87, 54) : Color.WHITE);
    }

    /**
     * Sets the script of the item to the "Runnable" code
     * 
     * @param run code to run
     */
    public void setOnActivate(Runnable run) {
        script = run;
    }

    /**
     * Runs the script associated with the item
     */
    public void activate() {
        if (script != null)
            script.run();
    }
}
