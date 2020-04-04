package com.ai;

import com.actors.FoodSource;
import com.actors.WorkerAnt;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.process.SearchFood;
import com.process.UsefulMath;
import com.ressource.Animation;

import java.util.Random;

public enum WorkerAntMovementState implements State<WorkerAnt> {

    SEARCH_FOR_FOOD_SOURCE() {
        @Override
        public void enter(WorkerAnt ant) {
            ant.setRotation(new Random().nextFloat() * 360);
            ant.sprite.switchAnimation(Animation.WORKERANT_WALKING);
        }

        @Override
        public void update(WorkerAnt ant) {
            FoodSource source = SearchFood.getClosestFoodSource(ant);
            if (source != null) {
                ant.setGoal(source);
                ant.movementStateMachine.changeState(GO_TO_FOOD_SOURCE);
            }
        }
    },

    GO_TO_FOOD_SOURCE() {
        @Override
        public void update(WorkerAnt ant) {
            if (UsefulMath.getDistanceActors(ant, ant.getGoal()) < 25) {
                ant.movementStateMachine.changeState(TAKE_FOOD);
            }
        }
    },

    TAKE_FOOD() {
        @Override
        public void enter(WorkerAnt ant) {
            ant.setGoal(null);
            ant.sprite.switchAnimation(Animation.WORKERANT_EATING);
        }

        @Override
        public void update(WorkerAnt ant) {
            if (ant.getCarrying() >= ant.getCarryPotential()) {
                ant.movementStateMachine.changeState(RETURN_TO_BASE_WITH_FOOD);
            }
        }
    },

    RETURN_TO_BASE_WITH_FOOD() {
        @Override
        public void update(WorkerAnt entity) {

        }
    };


    @Override
    public void enter(WorkerAnt entity) {

    }


    @Override
    public void exit(WorkerAnt entity) {

    }

    @Override
    public boolean onMessage(WorkerAnt entity, Telegram telegram) {
        return false;
    }
}
