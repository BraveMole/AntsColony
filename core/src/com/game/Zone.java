package com.game;

import com.actors.FoodSource;
import com.badlogic.gdx.utils.Array;

/* A zone consists of its coordinates, a list of the food source generated inside that zone, and what zones are neighbouring it*/

public class Zone {
    private float x;//Coordinates of the center of the square zone
    private float y;
    private float sizeOfZone;
    private Array<Zone> ArrayNeighbours;
    private Array<FoodSource> ArrayFoodSource;

    public Zone(float x, float y, float sizeOfZone) {
        this.ArrayFoodSource = new Array<>();
        this.sizeOfZone = sizeOfZone;
        this.x = x;
        this.y = y;
        this.ArrayFoodSource = new Array<>();
        this.ArrayNeighbours = new Array<>();
    }

    public float getSizeOfZone() {
        return sizeOfZone;
    }

    public Array<FoodSource> getArrayFoodSource() {
        return ArrayFoodSource;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void addNeighbour(Zone neighbour, boolean first) { //The boolean prevent an infinite adding loop
        this.ArrayNeighbours.add(neighbour);
        if (first) {
            neighbour.addNeighbour(this, false);
        }
    }

    public void addFoodSource(FoodSource foodSource) {
        this.ArrayFoodSource.add(foodSource);
    }

    public Array<Zone> getLNeighbour() {
        return this.ArrayNeighbours;
    }
}
