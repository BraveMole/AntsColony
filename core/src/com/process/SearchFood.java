package com.process;

import com.actors.FoodSource;
import com.actors.WorkerAnt;
import com.badlogic.gdx.utils.Array;
import com.game.Zone;

public class SearchFood {
    public static FoodSource getClosestFoodSource(WorkerAnt ant) {
        FoodSource close = null;
        float closestDistance = ant.getSensibility();
        for (FoodSource source : ant.getCurrentZone().getArrayFoodSource()) {
            if (UsefulMath.getDistanceActors(source, ant) < closestDistance) {
                closestDistance = UsefulMath.getDistanceActors(source, ant);
                close = source;
            }
        }
        if (close == null) {
            Array<Zone> LZones = new Array<>();
            for (Zone zone : ant.getCurrentZone().getLNeighbour()) {
                if (UsefulMath.isCloseToAZone(ant, zone)) {
                    LZones.add(zone);
                }
            }
            for (Zone zone : LZones) {
                for (FoodSource source : zone.getArrayFoodSource()) {
                    if (UsefulMath.getDistanceActors(source, ant) < closestDistance) {
                        close = source;
                        closestDistance = UsefulMath.getDistanceActors(source, ant);
                    }
                }
            }
        }
        return close;
    }

}
