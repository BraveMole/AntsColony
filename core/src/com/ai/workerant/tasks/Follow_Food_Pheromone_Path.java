package com.ai.workerant.tasks;

import com.actors.Pheromone;
import com.actors.WorkerAnt;
import com.ressource.PheromoneType;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.process.SearchFood;
import com.process.SearchPheromone;
import com.ressource.Animation;

public class Follow_Food_Pheromone_Path extends LeafTask<WorkerAnt> {

    public Follow_Food_Pheromone_Path() {
        this.setGuard(new Gard());
    }

    @Override
    public void start() {
        getObject().setPheromoneType(PheromoneType.HOME_PHEROMONE);
        getObject().sprite.switchAnimation(Animation.WORKER_ANT_WALKING);
        getObject().setWalking(true);
    }

    @Override
    public Status execute() {
        WorkerAnt ant = getObject();
        ant.setGoal(SearchFood.getClosestFoodSource(ant));
        ant.setRotation(ant.getPheromoneFollowed().getRotation());
        ant.stepForward();
        return Status.RUNNING;
    }

    @Override
    protected Task<WorkerAnt> copyTo(Task task) {
        return null;
    }

    class Gard extends Task<WorkerAnt> {

        @Override
        protected int addChildToTask(Task child) {
            return 0;
        }

        @Override
        public int getChildCount() {
            return 0;
        }

        @Override
        public Task<WorkerAnt> getChild(int i) {
            return null;
        }

        @Override
        public void run() {
            WorkerAnt ant = getObject();
            Pheromone foodPheromone = SearchPheromone.getClosestPheromone(ant.getX(), ant.getY(), ant.getCurrentZone(), ant.getCurrentZone().getFoodPheromoneGrid(), false);
            if (foodPheromone.getDistance() < Float.MAX_VALUE) {
                ant.setPheromoneFollowed(foodPheromone);
                this.status = Status.SUCCEEDED;
            } else {
                ant.setPheromoneType(PheromoneType.NO_PHEROMONE);
                this.status = Status.FAILED;
            }
        }

        @Override
        public void childSuccess(Task task) {

        }

        @Override
        public void childFail(Task task) {

        }

        @Override
        public void childRunning(Task runningTask, Task reporter) {

        }

        @Override
        protected Task copyTo(Task task) {
            return null;
        }
    }
}
