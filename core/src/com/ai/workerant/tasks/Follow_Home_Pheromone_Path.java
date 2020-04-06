package com.ai.workerant.tasks;

import com.actors.Pheromone;
import com.actors.WorkerAnt;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.process.SearchPheromone;
import com.process.UsefulMath;
import com.ressource.Animation;

public class Follow_Home_Pheromone_Path extends LeafTask<WorkerAnt> {

    @Override
    public void start() {
        getObject().sprite.switchAnimation(Animation.WORKER_ANT_WALKING);
        getObject().setWalking(true);
    }

    @Override
    public Status execute() {
        WorkerAnt ant = getObject();
        if (UsefulMath.getDistanceActors(ant, ant.getAnthill()) < 100) {
            return Status.SUCCEEDED;
        }
        Pheromone homePheromone = SearchPheromone.getClosestPheromone(ant.getX(), ant.getY(), ant.getCurrentZone(), ant.getCurrentZone().getHomePheromoneGrid(), false);
        if (homePheromone.getDistance() < Float.MAX_VALUE) {
            ant.setRotation(homePheromone.getRotation());
            ant.stepForward();
        } else {
            ant.randomStep();
        }
        return Status.RUNNING;
    }

    @Override
    protected Task<WorkerAnt> copyTo(Task<WorkerAnt> task) {
        return null;
    }
}
