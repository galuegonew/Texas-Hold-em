package menu;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class MenuNav {

    // Variables
    private VBox menuBox;
    private int current;

    /**
     * Constructor creates the menu navigation between items
     * 
     * @param scene       the scene to apply the events
     * @param menuBox     the pane that contains the menu
     * @param currentItem the current selected item
     */
    public MenuNav(Scene scene, VBox menuBox, int currentItem) {
        this.menuBox = menuBox;
        current = currentItem;

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (current > 0) {
                    getMenuItem(current).setActive(false);
                    getMenuItem(--current).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (current < menuBox.getChildren().size() - 1) {
                    getMenuItem(current).setActive(false);
                    getMenuItem(++current).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(current).activate();
            }
        });
    }

    /**
     * Returns the menu item corresponding to the given index
     * 
     * @param index the index of the item in the menu
     * @return the menu item at the given index
     */
    public MenuItem getMenuItem(int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }
}