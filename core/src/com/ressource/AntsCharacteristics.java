package com.ressource;

public enum AntsCharacteristics {
    WORKER_ANT(50, 100, 50, 5, 1, 1);


    private float speed;
    private float sensibility;
    private float movementDeviation;
    private float carryingPotential;
    private float carryingSpeed;
    private float pheromonePower;

    AntsCharacteristics(float speed, float sensibility, float movementDeviation, float carryingPotential, float carryingSpeed, float pheromonePower) {
        this.sensibility = sensibility;
        this.speed = speed;
        this.movementDeviation = movementDeviation;
        this.carryingPotential = carryingPotential;
        this.carryingSpeed = carryingSpeed;
        this.pheromonePower = pheromonePower;
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

    public float getPheromonePower() {
        return pheromonePower;
    }


}
