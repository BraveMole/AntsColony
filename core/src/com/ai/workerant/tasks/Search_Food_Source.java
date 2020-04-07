package com.ai.workerant.tasks;

import com.actors.FoodSource;
import com.actors.WorkerAnt;
import com.ressource.PheromoneType;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.MathUtils;
import com.process.SearchFood;
import com.process.SearchPheromone;
import com.process.UsefulMath;
import com.ressource.Animation;

public class Search_Food_Source extends LeafTask<WorkerAnt> {

    public void start() {
        WorkerAnt ant = getObject();
        ant.sprite.switchAnimation(Animation.WORKER_ANT_WALKING);
        ant.setRotation(MathUtils.random(0, 360));
        ant.setWalking(true);
    }

    @Override
    public Status execute() {
        WorkerAnt ant = getObject();
        if (ant.getCurrentDistance() >= ant.getMaxDistance()) {
            ant.setPheromoneType(PheromoneType.NO_PHEROMONE);
            return Status.FAILED;
        }
        FoodSource source = SearchFood.getClosestFoodSource(ant);
        if (source != null
                | SearchPheromone.getClosestPheromone(ant.getX(), ant.getY(), ant.getCurrentZone(), ant.getCurrentZone().getFoodPheromoneGrid(), false).getDistance() < Float.MAX_VALUE) {
            ant.setGoal(source);
            return Status.SUCCEEDED;
        }
        ant.randomStep();
        if (UsefulMath.getDistanceActors(ant, ant.getAnthill()) > 100) {
            ant.setPheromoneType(PheromoneType.HOME_PHEROMONE);
        }
        return Status.RUNNING;
    }

    @Override
    public void end() {
        getObject().setWalking(false);
        getObject().sprite.switchAnimation(Animation.WORKER_ANT_STILL);
    }

    @Override
    protected Task copyTo(Task task) {
        return null;
    }
}
