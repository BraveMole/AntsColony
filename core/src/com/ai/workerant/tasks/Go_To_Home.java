package com.ai.workerant.tasks;

import com.actors.WorkerAnt;
import com.ressource.PheromoneType;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.process.UsefulMath;
import com.ressource.Animation;

public class Go_To_Home extends LeafTask<WorkerAnt> {

    @Override
    public void start() {
        WorkerAnt ant = getObject();
        ant.setPheromoneType(PheromoneType.NO_PHEROMONE);
        ant.setGoal(ant.getAnthill());
    }

    @Override
    public Status execute() {
        WorkerAnt ant = getObject();
        if (UsefulMath.getDistanceActors(ant, ant.getAnthill()) < 30) {
            ant.resetCarry();
            ant.resetDistance();
            ant.sprite.switchAnimation(Animation.WORKER_ANT_STILL);
            ant.setWalking(false);
            //ant.getBehaviorTree().resetTask();
            return Status.SUCCEEDED;
        } else {
            ant.stepToGoal();
            return Status.RUNNING;
        }
    }

    @Override
    protected Task<WorkerAnt> copyTo(Task<WorkerAnt> task) {
        return null;
    }
}
