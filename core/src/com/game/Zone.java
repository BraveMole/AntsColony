package com.game;

import com.actors.FoodSource;
import com.actors.Pheromone;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import static com.game.World.mainWorld;

/* A zone consists of its coordinates, a list of the food source generated inside that zone, and what zones are neighbouring it*/

public class Zone extends Actor {
    private float x;//Coordinates of the center of the square zone
    private float y;
    private float sizeOfZone;
    private float timeElapsed;

    private Array<Array<Pheromone>> homePheromoneGrid;
    private Array<Array<Pheromone>> foodPheromoneGrid;
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
        this.foodPheromoneGrid = new Array<>();
        for (int i = 0; i < sizeOfZone / mainWorld.getSettings().getDistanceBetweenPheromones(); i++) {
            this.homePheromoneGrid.add(new Array<Pheromone>());
            this.foodPheromoneGrid.add(new Array<Pheromone>());
            for (int j = 0; j < sizeOfZone / mainWorld.getSettings().getDistanceBetweenPheromones(); j++) {
                this.homePheromoneGrid.get(i).add(new Pheromone());
                this.foodPheromoneGrid.get(i).add(new Pheromone());
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

    public Array<Array<Pheromone>> getFoodPheromoneGrid() {
        return foodPheromoneGrid;
    }

    private void pheromoneEvaporation() {
        for (int i = 0; i < sizeOfZone / 10; i++) {
            for (int j = 0; j < sizeOfZone / 10; j++) {
                this.homePheromoneGrid.get(i).get(j).evaporate(mainWorld.getSettings().getPheromoneEvaporationRate());
                this.foodPheromoneGrid.get(i).get(j).evaporate(mainWorld.getSettings().getPheromoneEvaporationRate());
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

    public Array<Array<Pheromone>> getHomePheromoneGrid() {
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
