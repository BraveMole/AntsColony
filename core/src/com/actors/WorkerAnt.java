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
import com.ressource.TextureLoader;

import java.util.Random;

public class WorkerAnt extends Actor {
    static Random rand = new Random();

    static float defaultSpeed;//Number of units for 1 second
    static float defaultSensibility = 100;
    static float deviation;//Deviation of gaussian law for random movement in degrees
    public StateMachine<WorkerAnt, WorkerAntMovementState> movementStateMachine;
    public AnimatedSprite sprite;
    private float speed;
    private Actor goal;
    private Vector2 movement;
    private Zone currentZone;
    private Pheromone pheromone;
    private float sensibility;
    private float elapsedTimeBetweenDecision;
    private float carryPotential;
    private float carrying;

    public WorkerAnt(float x, float y, float rotation) {
        this.setX(x);
        this.setY(y);
        this.movement = new Vector2();
        this.elapsedTimeBetweenDecision = 0;
        this.setPheromone(Pheromone.NO_PHEROMONE);
        this.setCurrentZone(SearchZone.searchZone(x, y));
        this.setRotation(rotation);
        this.speed = WorkerAnt.defaultSpeed;
        this.sensibility = WorkerAnt.defaultSensibility;
        this.movementStateMachine = new DefaultStateMachine<>(this, WorkerAntMovementState.SEARCH_FOR_FOOD_SOURCE);
        this.goal = null;
        this.speed = 50f;
        deviation = 9;
        this.sprite = new AnimatedSprite(1 / 6f, TextureLoader.ants, TextureLoader.antsCycle, x, y, this.getRotation(), 25, 37);
    }

    public float getCarrying() {
        return carrying;
    }

    public void setCarrying(float carrying) {
        this.carrying = carrying;
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

    public StateMachine<WorkerAnt, WorkerAntMovementState> getMovementStateMachine() {
        return movementStateMachine;
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
    }

    public void act(float delta) {
        this.update(delta);
        switch (movementStateMachine.getCurrentState()) {
            case SEARCH_FOR_FOOD_SOURCE:
                randomStep(delta);
                move();
                break;
            case GO_TO_FOOD_SOURCE:
                stepToGoal(delta);
                move();
                break;


        }
        switch (this.getPheromone()) {
            case NO_PHEROMONE:
                break;
            case FOOD_SOURCE_PHEROMONE:
                break;
            case HOME_PHEROMONE:
                break;
        }

    }

    public void update(float delta) {
        this.elapsedTimeBetweenDecision += delta;
        if (this.elapsedTimeBetweenDecision > 0.5f) {
            this.elapsedTimeBetweenDecision = 0;
            movementStateMachine.update();
        }
    }


    private void randomStep(float delta) {
        this.rotateBy((float) (rand.nextGaussian() * WorkerAnt.deviation) * delta);
        movement = UsefulMath.setVector(this.getRotation(), speed * delta);
    }

    private void stepToGoal(float delta) {
        movement = UsefulMath.setVector(this, goal, speed * delta);
        this.setRotation(movement.angle());
    }

}

