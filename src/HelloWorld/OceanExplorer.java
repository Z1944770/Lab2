package HelloWorld;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.awt.Point;

public class OceanExplorer extends Application {
    private final int dimensions = 10;
    private final int scale = 50;
    private Ship ship;
    private OceanMap oceanMap;
    private AnchorPane root;
    private ImageView shipImageView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage oceanStage) {
        oceanMap = new OceanMap(dimensions);
        ship = new Ship(oceanMap);
        root = new AnchorPane();

        Scene scene = new Scene(root, dimensions * scale, dimensions * scale);
        oceanStage.setScene(scene);
        oceanStage.setTitle("ColumbusGame");
        oceanStage.show();

        drawMap();
        loadShipImage();

        startSailing(scene);
    }

    private void drawMap() {
        boolean[][] oceanGrid = oceanMap.getMap();

        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                rect.setStroke(Color.BLACK);
                rect.setFill(Color.PALETURQUOISE);
                root.getChildren().add(rect);
            }
        }
    }

    private void loadShipImage() {
        // Assuming the image "ship.png" is located in the "images" folder within your project directory
        String imageUrl = "file:///" + System.getProperty("user.dir").replace("\\", "/") + "/images/ship.png";
        Image shipImage = new Image(imageUrl, 50, 50, true, true);
        shipImageView = new ImageView(shipImage);
        shipImageView.setX(ship.getShipLocation().getX() * scale);
        shipImageView.setY(ship.getShipLocation().getY() * scale);
        root.getChildren().add(shipImageView);
    }

    private void startSailing(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case RIGHT:
                        ship.goEast();
                        break;
                    case LEFT:
                        ship.goWest();
                        break;
                    case UP:
                        ship.goNorth();
                        break;
                    case DOWN:
                        ship.goSouth();
                        break;
                    default:
                        break;
                }

                shipImageView.setX(ship.getShipLocation().getX() * scale);
                shipImageView.setY(ship.getShipLocation().getY() * scale);
            }
        });
        root.requestFocus();
    }
}
