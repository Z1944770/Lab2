package HelloWorld;

import javafx.geometry.Point2D;

public class Ship {
    private Point2D currentLocation;
    private OceanMap oceanMap;

    public Ship(OceanMap oceanMap) {
        this.oceanMap = oceanMap;
        this.currentLocation = getRandomEmptyLocation();
    }

    public Point2D getShipLocation() {
        return currentLocation;
    }

    public void goEast() {
        double newX = currentLocation.getX() + 1;
        if (newX < oceanMap.getDimensions() && !oceanMap.isIsland((int) newX, (int) currentLocation.getY())) {
            currentLocation = new Point2D(newX, currentLocation.getY());
        }
    }

    public void goWest() {
        double newX = currentLocation.getX() - 1;
        if (newX >= 0 && !oceanMap.isIsland((int) newX, (int) currentLocation.getY())) {
            currentLocation = new Point2D(newX, currentLocation.getY());
        }
    }

    public void goNorth() {
        double newY = currentLocation.getY() - 1;
        if (newY >= 0 && !oceanMap.isIsland((int) currentLocation.getX(), (int) newY)) {
            currentLocation = new Point2D(currentLocation.getX(), newY);
        }
    }

    public void goSouth() {
        double newY = currentLocation.getY() + 1;
        if (newY < oceanMap.getDimensions() && !oceanMap.isIsland((int) currentLocation.getX(), (int) newY)) {
            currentLocation = new Point2D(currentLocation.getX(), newY);
        }
    }

    private Point2D getRandomEmptyLocation() {
        Point2D location;
        do {
            location = new Point2D(Math.random() * oceanMap.getDimensions(), Math.random() * oceanMap.getDimensions());
        } while (oceanMap.isIsland((int) location.getX(), (int) location.getY()));
        return location;
    }
}

