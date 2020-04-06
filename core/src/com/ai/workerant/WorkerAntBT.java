package com.ai.workerant;

import com.actors.WorkerAnt;
import com.ai.workerant.tasks.*;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.DynamicGuardSelector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.AlwaysSucceed;
import com.badlogic.gdx.ai.btree.decorator.UntilSuccess;

public abstract class WorkerAntBT {

    static public void createBT(WorkerAnt ant) {
        Sequence<WorkerAnt> sequence00 = new Sequence<>();
        BehaviorTree<WorkerAnt> BT = new BehaviorTree<>(sequence00, ant);
        Sequence<WorkerAnt> sequence10 = new Sequence<>();
        Sequence<WorkerAnt> sequence11 = new Sequence<>();
        Sequence<WorkerAnt> sequence30 = new Sequence<>();
        AlwaysSucceed<WorkerAnt> alwaysSucceed01 = new AlwaysSucceed<>();
        UntilSuccess<WorkerAnt> untilSuccess02 = new UntilSuccess<>();
        DynamicGuardSelector<WorkerAnt> dynamicGuardSelector21 = new DynamicGuardSelector<>();
        sequence00.addChild(alwaysSucceed01);
        sequence00.addChild(untilSuccess02);
        alwaysSucceed01.addChild(sequence10);
        sequence10.addChild(new Search_Food_Source());
        sequence10.addChild(dynamicGuardSelector21);
        dynamicGuardSelector21.addChild(sequence30);
        sequence30.addChild(new Go_To_Food_Source());
        sequence30.addChild(new Take_Food());
        sequence30.setGuard(new GuardSequence30());
        dynamicGuardSelector21.addChild(new Follow_Food_Pheromone_Path());
        untilSuccess02.addChild(sequence11);
        sequence11.addChild(new Follow_Home_Pheromone_Path());
        sequence11.addChild(new Go_To_Home());
        ant.setBehaviorTree(BT);
    }

    private static class GuardSequence30 extends Task<WorkerAnt> {

        @Override
        protected int addChildToTask(Task child) {
            return 0;
        }

        @Override
        public int getChildCount() {
            return 0;
        }

        @Override
        public Task getChild(int i) {
            return null;
        }

        @Override
        public void run() {
            if (getObject().getGoal() != null) {
                this.status = Status.SUCCEEDED;
            } else {
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
