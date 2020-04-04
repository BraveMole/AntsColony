package com.game;

import com.actors.FoodSource;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import static com.game.World.mainWorld;

/* A zone consists of its coordinates, a list of the food source generated inside that zone, and what zones are neighbouring it*/

public class Zone extends Actor {
    private float x;//Coordinates of the center of the square zone
    private float y;
    private float sizeOfZone;
    private float timeElapsed;

    private Array<Array<Vector2>> homePheromoneGrid;
    private Array<Zone> ArrayNeighbours;
    private Array<FoodSource> ArrayFoodSource;

    public Zone(float x, float y, int sizeOfZone) {
        this.ArrayFoodSource = new Array<>();
        this.sizeOfZone = sizeOfZone;
        this.x = x;
        this.y = y;
        this.timeElapsed = 0;
        this.ArrayFoodSource = new Array<>();
        this.ArrayNeighbours = new Array<>();
        this.homePheromoneGrid = new Array<>();
        for (int i = 0; i < sizeOfZone / mainWorld.getSettings().getDistanceBetweenPheromones(); i++) {
            this.homePheromoneGrid.add(new Array<Vector2>());
            for (int j = 0; j < sizeOfZone / mainWorld.getSettings().getDistanceBetweenPheromones(); j++) {
                this.homePheromoneGrid.get(i).add(new Vector2());
            }
        }
    }

    @Override
    public void act(float delta) {
        timeElapsed += delta;
        if (timeElapsed >= 1) {
            this.pheromoneEvaporation();
        }
    }

    private void pheromoneEvaporation() {
        for (int i = 0; i < sizeOfZone / 10; i++) {
            for (int j = 0; j < sizeOfZone / 10; j++) {
                this.homePheromoneGrid.get(i).get(j).scl(mainWorld.getSettings().getPheromoneEvaporationRate());
            }
        }
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

    public Array<Array<Vector2>> getHomePheromoneGrid() {
        return homePheromoneGrid;
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
