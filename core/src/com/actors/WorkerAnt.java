package com.actors;

import com.ai.PheromoneType;
import com.ai.workerant.WorkerAntBT;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.Zone;
import com.process.SearchPheromone;
import com.process.SearchZone;
import com.process.UsefulMath;
import com.ressource.AnimatedSprite;
import com.ressource.Animation;
import com.ressource.AntsCharacteristics;
import org.graalvm.compiler.lir.LIRInstruction;
import utils.BehaviorTreeViewer;

import static com.game.World.mainWorld;

public class WorkerAnt extends Actor {
    private PheromoneType pheromoneType;

    public AnimatedSprite sprite;
    private float speed;
    private AnthillEntrance anthill;
    private Actor goal;
    private float maxDistance;
    private Vector2 movement;
    private Zone currentZone;
    private float movementDeviation;
    private float sensibility;
    private float elapsedTimeBetweenDecision;
    private float carryPotential;
    private float carryingSpeed;
    private float carrying;
    private float currentDistance;
    private Pheromone pheromoneFollowed;
    private float pheromonePower;
    private BehaviorTree<WorkerAnt> behaviorTree;
    private boolean walking;
    private boolean eating;

    public WorkerAnt(float x, float y, float rotation) {
        this.setX(x);
        this.setY(y);
        this.movement = new Vector2();
        this.elapsedTimeBetweenDecision = 0;
        this.setCurrentZone(SearchZone.searchZone(x, y));
        this.setRotation(rotation);
        this.sprite = new AnimatedSprite(Animation.WORKER_ANT_STILL, x, y, this.getRotation());
        this.pheromoneType = PheromoneType.NO_PHEROMONE;
        this.goal = null;
        this.setCharacteristics(AntsCharacteristics.WORKER_ANT);
        WorkerAntBT.createBT(this);
    }

    public BehaviorTree<WorkerAnt> getBehaviorTree() {
        return behaviorTree;
    }

    public void setBehaviorTree(BehaviorTree<WorkerAnt> behaviorTree) {
        this.behaviorTree = behaviorTree;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    public void setEating(boolean eating) {
        this.eating = eating;
    }

    public void setAnthill(AnthillEntrance anthill) {
        this.anthill = anthill;
    }

    public BehaviorTreeViewer<WorkerAnt> createBehaviorTreeViewer(Skin skin) {
        return new BehaviorTreeViewer(this.behaviorTree, false, skin);
    }

    private void setCharacteristics(AntsCharacteristics characteristics) {
        this.speed = characteristics.getSpeed();
        this.sensibility = characteristics.getSensibility();
        this.movementDeviation = characteristics.getMovementDeviation();
        this.carryPotential = characteristics.getCarryingPotential();
        this.carryingSpeed = characteristics.getCarryingSpeed();
        this.maxDistance = characteristics.getDistanceMax();
        this.pheromonePower = characteristics.getPheromonePower();
        this.currentDistance = 0;
        this.eating = false;
        this.walking = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.sprite.animate(Gdx.graphics.getDeltaTime());
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.setRotation(this.getRotation() - 90);
        this.sprite.draw(batch);
    }

    public PheromoneType getPheromoneType() {
        return pheromoneType;
    }

    public void setPheromoneType(PheromoneType pheromoneType) {
        this.pheromoneType = pheromoneType;
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

    public Pheromone getPheromoneFollowed() {
        return pheromoneFollowed;
    }

    public void setPheromoneFollowed(Pheromone pheromoneFollowed) {
        this.pheromoneFollowed = pheromoneFollowed;
    }

    public AnthillEntrance getAnthill() {
        return anthill;
    }

    public void resetDistance() {
        this.currentDistance = 0;
    }

    public void resetCarry() {
        this.carrying = 0;
    }

    public float getCurrentDistance() {
        return currentDistance;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone currentZone) {
        this.currentZone = currentZone;
    }

    public float getMaxDistance() {
        return maxDistance;
    }

    private void move(float delta) {
        this.moveBy(movement.x * delta, movement.y * delta);
        currentDistance += movement.len() * delta;
        this.setCurrentZone(SearchZone.searchZone(this.getX(), this.getY()));
        this.putPheromone();
    }

    private void putPheromone() {
        if (!this.pheromoneType.equals(PheromoneType.NO_PHEROMONE)) {
            SearchPheromone.getCurrentPheromone(this).replacePheromone(this.movement.cpy().rotate(180), this.currentDistance, this.pheromonePower);
        }
    }

    public void act(float delta) {
        this.update(delta);
        if (walking) {
            this.move(delta);
        } else if (eating) {
            this.takeFood(delta);
        }
    }

    public void update(float delta) {
        this.elapsedTimeBetweenDecision += delta;
        if (this.elapsedTimeBetweenDecision > mainWorld.getSettings().getAICycleTime()) {
            this.elapsedTimeBetweenDecision = 0;
            this.behaviorTree.step();
        }
    }


    public void randomStep() {
        this.rotateBy(UsefulMath.nextGaussian(movementDeviation));
        movement = UsefulMath.setVector(this.getRotation(), this.speed);
    }

    public void stepToGoal() {
        movement = UsefulMath.setVector(this, goal, speed);
        this.setRotation(movement.angle());
    }

    public void stepForward() {
        movement = UsefulMath.setVector(this.getRotation(), speed);
    }

    private void takeFood(float delta) {
        float quantity = ((FoodSource) goal).giveFood(this.carryingSpeed * delta);
        this.carrying += quantity;
        if (this.carrying >= this.carryPotential) {
            ((FoodSource) goal).takeFood(this.carrying - this.carryPotential);
            this.carrying = this.carryPotential;
        }
    }

}

