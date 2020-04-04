package com.process;

import com.actors.AnthillEntrance;
import com.actors.FoodSource;
import com.actors.WorkerAnt;
import com.game.World;
import com.game.Zone;
import com.ressource.WorldSettings;

import java.util.Objects;

import static com.game.World.mainWorld;

public class WorldCreation {
    public static void Creation(WorldSettings settings) {
        mainWorld = new World(settings);
        createZones(settings);
        addActors();
    }

    private static void createZones(WorldSettings settings) {
        int numberOfXZones = (int) (Math.ceil(settings.getxLimit() / settings.getZoneSize()));
        int numberOfYZones = (int) (Math.ceil(settings.getyLimit() / settings.getZoneSize()));
        for (int i = 0; i < numberOfXZones; i++) {
            mainWorld.addColumn();
            for (int j = 0; j < numberOfYZones; j++) {
                mainWorld.addZone(i, j, j == (numberOfYZones - 1), new Zone(i * settings.getZoneSize(), j * settings.getZoneSize(), settings.getZoneSize()));
            }
        }
    }

    private static void addActors() {
        AnthillEntrance anthillEntrance = new AnthillEntrance(500, 500);
        mainWorld.addEnvironment(anthillEntrance);
        addAnt(200, 200, 0, anthillEntrance);
        addFoodSource(400, 200, 10);
    }

    private static void addAnt(float x, float y, float rotation, AnthillEntrance anthillEntrance) {
        WorkerAnt worker = new WorkerAnt(x, y, rotation);
        mainWorld.addAnt(worker);
        worker.setAnthill(anthillEntrance);
    }

    private static void addFoodSource(float x, float y, float foodQuantity) {
        FoodSource source = new FoodSource(x, y, foodQuantity);
        mainWorld.addEnvironment(source);
        Objects.requireNonNull(SearchZone.searchZone(x, y)).addFoodSource(source);
    }
}
