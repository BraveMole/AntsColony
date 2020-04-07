package com.ai.workerant.tasks;

import com.actors.WorkerAnt;
import com.ressource.PheromoneType;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.process.UsefulMath;
import com.ressource.Animation;

public class Go_To_Food_Source extends LeafTask<WorkerAnt> {
    public void start() {
        getObject().sprite.switchAnimation(Animation.WORKER_ANT_WALKING);
        getObject().setPheromoneType(PheromoneType.HOME_PHEROMONE);
        getObject().setWalking(true);
    }

    @Override
    public Status execute() {
        WorkerAnt ant = getObject();
        if (ant.getGoal() != null) {
            if (UsefulMath.getDistanceActors(ant, ant.getGoal()) < 25) {
                return Status.SUCCEEDED;
            }
        } else {
            return Status.FAILED;
        }
        ant.stepToGoal();
        return Status.RUNNING;
    }

    @Override
    protected Task copyTo(Task task) {
        return null;
    }
}
