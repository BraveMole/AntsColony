package com.ressource;

public enum AntsCharacteristics {
    WORKER_ANT(50f, 100f, 10f, 5f, 1f, 200f, 10f);


    private float speed;
    private float sensibility;
    private float movementDeviation;
    private float carryingPotential;
    private float carryingSpeed;

    private float distanceMax;
    private float pheromonePower;

    AntsCharacteristics(float speed, float sensibility, float movementDeviation, float carryingPotential, float carryingSpeed, float distanceMax, float pheromonePower) {
        this.speed = speed;
        this.sensibility = sensibility;
        this.movementDeviation = movementDeviation;
        this.carryingPotential = carryingPotential;
        this.carryingSpeed = carryingSpeed;
        this.distanceMax = distanceMax;
        this.pheromonePower = pheromonePower;
    }

    public float getPheromonePower() {
        return pheromonePower;
    }

    public float getDistanceMax() {
        return distanceMax;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSensibility() {
        return sensibility;
    }

    public float getMovementDeviation() {
        return movementDeviation;
    }

    public float getCarryingPotential() {
        return carryingPotential;
    }

    public float getCarryingSpeed() {
        return carryingSpeed;
    }


}
