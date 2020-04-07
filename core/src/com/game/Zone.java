package com.game;

import com.actors.FoodSource;
import com.actors.Pheromone;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import static com.game.World.mainWorld;

/* A zone consists of its coordinates, a list of the food source generated inside that zone, and what zones are neighbouring it*/

public class Zone extends Actor {
    private float x;//Coordinates of the center of the square zone
    private float y;
    private float timeElapsed;

    private Array<Array<Pheromone>> homePheromoneGrid;
    private Array<Array<Pheromone>> foodPheromoneGrid;
    private Array<Zone> ArrayNeighbours;
    private Array<FoodSource> arrayFoodSource;

    public Zone(float x, float y, int sizeOfZone) {
        this.arrayFoodSource = new Array<>();
        this.x = x;
        this.y = y;
        this.timeElapsed = 0;
        this.arrayFoodSource = new Array<>();
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
        if (timeElapsed >= mainWorld.getSettings().getAICycleTime()) {
            timeElapsed = 0;
            this.pheromoneEvaporation();
        }
    }

    public Array<Array<Pheromone>> getFoodPheromoneGrid() {
        return foodPheromoneGrid;
    }

    private void pheromoneEvaporation() {
        for (int i = 0; i < mainWorld.getSettings().getZoneSize() / mainWorld.getSettings().getDistanceBetweenPheromones(); i++) {
            for (int j = 0; j < mainWorld.getSettings().getZoneSize() / mainWorld.getSettings().getDistanceBetweenPheromones(); j++) {
                this.homePheromoneGrid.get(i).get(j).evaporate(mainWorld.getSettings().getPheromoneEvaporationRate());
                this.foodPheromoneGrid.get(i).get(j).evaporate(mainWorld.getSettings().getPheromoneEvaporationRate());
            }
        }
    }

    public boolean removeFoodSource(FoodSource source) {
        return this.arrayFoodSource.removeValue(source, true);
    }

    public Array<FoodSource> getArrayFoodSource() {
        return arrayFoodSource;
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
        this.arrayFoodSource.add(foodSource);
    }

    public Array<Zone> getLNeighbour() {
        return this.ArrayNeighbours;
    }
}
