package com.process;

import com.actors.Pheromone;
import com.actors.WorkerAnt;
import com.badlogic.gdx.utils.Array;
import com.game.Zone;

import static com.game.World.mainWorld;

public class SearchPheromone {

    public static Pheromone getCurrentPheromone(WorkerAnt ant) {
        switch (ant.getPheromoneType()) {
            case HOME_PHEROMONE:
                return getClosestPheromone(ant.getX(), ant.getY(), ant.getCurrentZone(), ant.getCurrentZone().getHomePheromoneGrid(), true);
            case FOOD_SOURCE_PHEROMONE:
                return getClosestPheromone(ant.getX(), ant.getY(), ant.getCurrentZone(), ant.getCurrentZone().getFoodPheromoneGrid(), true);
            default:
                return null;
        }
    }

    /**
     * @param x
     * @param y
     * @param currentZone
     * @param pheromoneGrid
     * @param isReplacingPheromone if true, will give the pheromone the ant is directly above
     * @return Gives a Pheromone from the pheromoneGrid. If isreplacingPheromone is false, it will give the best (ie mindistance) pheromone around the ant. If all pheromones are max distance (ie, no ants put pheromone there before or it evaporated),
     * the boolean Pheromone.getDistance() '<' Float.MAX_VALUE will be false;
     */
    public static Pheromone getClosestPheromone(float x, float y, Zone currentZone, Array<Array<Pheromone>> pheromoneGrid, boolean isReplacingPheromone) {
        int indexX = (int) ((x - currentZone.getX()) / (mainWorld.getSettings().getDistanceBetweenPheromones()));
        int indexY = (int) ((y - currentZone.getY()) / (mainWorld.getSettings().getDistanceBetweenPheromones()));

        Pheromone bestPheromone = pheromoneGrid.get(indexX).get(indexY);
        float minDistance = bestPheromone.getDistance();
        if (minDistance < Float.MAX_VALUE | isReplacingPheromone) {
            return bestPheromone;
        }

        Pheromone pheromone;
        if (indexX >= 1 & indexX < (mainWorld.getSettings().getZoneSize() / mainWorld.getSettings().getDistanceBetweenPheromones() - 1) & indexY >= 1 & indexY < (mainWorld.getSettings().getZoneSize() / mainWorld.getSettings().getDistanceBetweenPheromones()) - 1) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i != 0 | j != 0) {
                        pheromone = pheromoneGrid.get(indexX + i).get(indexY + j);
                        if ((pheromone.getDistance() < bestPheromone.getDistance() & pheromone.getDirection().len() > 0.1f)) {
                            bestPheromone = pheromone;
                        }
                    }
                }
            }
        }
        return bestPheromone;
    }
}
