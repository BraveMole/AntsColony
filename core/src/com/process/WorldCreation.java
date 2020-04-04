package com.process;

import com.actors.AnthillEntrance;
import com.actors.FoodSource;
import com.actors.WorkerAnt;
import com.game.World;
import com.game.Zone;

import java.util.Objects;

import static com.game.World.mainWorld;

public class WorldCreation {
    public static void Creation(float sizeOfZone, int numberOfZones) {
        mainWorld = new World(sizeOfZone);
        for (int i = 0; i < numberOfZones; i++) {
            mainWorld.addColumn();
            for (int j = 0; j < numberOfZones; j++) {
                mainWorld.addZone(i, j, j == numberOfZones - 1, new Zone(i * sizeOfZone + sizeOfZone / 2, j * sizeOfZone + sizeOfZone / 2, sizeOfZone));
            }
        }
        mainWorld.getRoot().addActor(new WorkerAnt(200, 200, 0));
        mainWorld.getRoot().addActor(new AnthillEntrance(500, 500));
        addFoodSource(400, 200, 10);
    }

    private static void addFoodSource(float x, float y, float foodQuantity) {
        FoodSource source = new FoodSource(x, y, foodQuantity);
        mainWorld.getRoot().addActor(source);
        Objects.requireNonNull(SearchZone.searchZone(x, y)).addFoodSource(source);
    }
}
