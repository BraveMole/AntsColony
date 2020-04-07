package com.ai.workerant.tasks;

import com.actors.FoodSource;
import com.actors.WorkerAnt;
import com.ressource.PheromoneType;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.ressource.Animation;

public class Take_Food extends LeafTask<WorkerAnt> {

    @Override
    public void start() {
        WorkerAnt ant = getObject();
        ant.setWalking(false);
        ant.setEating(true);
        getObject().sprite.switchAnimation(Animation.WORKER_ANT_EATING);
    }

    @Override
    public Status execute() {
        WorkerAnt ant = getObject();
        if (ant.getCarrying() >= ant.getCarryPotential() | ((FoodSource) ant.getGoal()).getFoodQuantity() == 0) {
            ant.setPheromoneType(PheromoneType.FOOD_SOURCE_PHEROMONE);
            ant.setEating(false);
            return Status.SUCCEEDED;
        }
        return Status.RUNNING;
    }

    @Override
    protected Task copyTo(Task task) {
        return null;
    }
}
