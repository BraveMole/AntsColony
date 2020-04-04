package com.actors;

import com.ai.Pheromone;
import com.ai.WorkerAntMovementState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Zone;
import com.process.SearchZone;
import com.process.UsefulMath;
import com.ressource.AnimatedSprite;
import com.ressource.Animation;
import com.ressource.AntsCharacteristics;

public class WorkerAnt extends Actor {
    public StateMachine<WorkerAnt, WorkerAntMovementState> movementStateMachine;
    public AnimatedSprite sprite;
    private float speed;
    private AnthillEntrance anthill;
    private Actor goal;
    private Pheromone pheromone;
    private Vector2 movement;
    private Zone currentZone;
    private float movementDeviation;
    private float sensibility;
    private float elapsedTimeBetweenDecision;
    private float carryPotential;
    private float carryingSpeed;
    private float carrying;
    private float pheromonePower;
    public WorkerAnt(float x, float y, float rotation) {
        this.setX(x);
        this.setY(y);
        this.movement = new Vector2();
        this.elapsedTimeBetweenDecision = 0;
        this.setCurrentZone(SearchZone.searchZone(x, y));
        this.setRotation(rotation);
        this.sprite = new AnimatedSprite(Animation.WORKERANT_WALKING, x, y, this.getRotation());
        this.movementStateMachine = new DefaultStateMachine<>(this);
        this.movementStateMachine.changeState(WorkerAntMovementState.SEARCH_FOR_FOOD_SOURCE);
        this.goal = null;
        this.setCharacteristics(AntsCharacteristics.WORKER_ANT);
    }

    public void setAnthill(AnthillEntrance anthill) {
        this.anthill = anthill;
    }

    private void setCharacteristics(AntsCharacteristics characteristics) {
        this.speed = characteristics.getSpeed();
        this.sensibility = characteristics.getSensibility();
        this.movementDeviation = characteristics.getMovementDeviation();
        this.carryPotential = characteristics.getCarryingPotential();
        this.carryingSpeed = characteristics.getCarryingSpeed();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.sprite.animate(Gdx.graphics.getDeltaTime());
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.setRotation(this.getRotation() - 90);
        this.sprite.draw(batch);
    }

    public Pheromone getPheromone() {
        return pheromone;
    }

    public void setPheromone(Pheromone pheromone) {
        this.pheromone = pheromone;
    }

    public float getCarrying() {
        return carrying;
    }

    public float getCarryPotential() {
        return carryPotential;
    }

    public Actor getGoal() {
        return goal;
    }

    public void setGoal(Actor goal) {
        this.goal = goal;
    }

    public float getSensibility() {
        return sensibility;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone currentZone) {
        this.currentZone = currentZone;
    }

    private void move() {
        this.moveBy(movement.x, movement.y);
        this.setCurrentZone(SearchZone.searchZone(this.getX(), this.getY()));
        this.putPheromone();
    }

    private void putPheromone() {
        UsefulMath.getCurrentPheromone(this).add(movement.rotate(180));
    }

    public void act(float delta) {
        this.update(delta);
        switch (movementStateMachine.getCurrentState()) {
            case SEARCH_FOR_FOOD_SOURCE:
                randomStep(delta);
                move();
                break;
            case GO_TO_FOOD_SOURCE:
            case RETURN_TO_BASE_WITH_FOOD:
                stepToGoal(delta);
                move();
                break;
            case TAKE_FOOD:
                takeFood(delta);
                break;


        }

    }

    public void update(float delta) {
        this.elapsedTimeBetweenDecision += delta;
        if (this.elapsedTimeBetweenDecision > 0.1f) {
            this.elapsedTimeBetweenDecision = 0;
            movementStateMachine.update();
        }
    }

    public void setAnthillAsGoal() {
        this.setGoal(this.anthill);
    }


    private void randomStep(float delta) {
        this.rotateBy(UsefulMath.nextGaussian(movementDeviation) * delta);
        movement = UsefulMath.setVector(this.getRotation(), speed * delta);
    }

    private void stepToGoal(float delta) {
        movement = UsefulMath.setVector(this, goal, speed * delta);
        this.setRotation(movement.angle());
    }

    private void takeFood(float delta) {
        float quantity = ((FoodSource) goal).giveFood(this.carryingSpeed * delta);
        if (quantity == 0) {
            this.movementStateMachine.changeState(WorkerAntMovementState.RETURN_TO_BASE_WITH_FOOD);
        }
        this.carrying += quantity;
        if (this.carrying >= this.carryPotential) {
            ((FoodSource) goal).takeFood(this.carrying - this.carryPotential);
            this.carrying = this.carryPotential;
            this.movementStateMachine.changeState(WorkerAntMovementState.RETURN_TO_BASE_WITH_FOOD);
        }
    }

}

