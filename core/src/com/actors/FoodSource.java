package com.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ressource.AnimatedSprite;
import com.ressource.TextureLoader;

public class FoodSource extends Actor {
    private float foodQuantity;
    private AnimatedSprite sprite;

    public FoodSource(float x, float y, float foodQuantity) {
        this.foodQuantity = foodQuantity;
        this.setX(x);
        this.setY(y);
        this.sprite = new AnimatedSprite(TextureLoader.foodSource, x, y, 0, foodQuantity, foodQuantity);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.sprite.draw(batch);
    }

    public float removeFood(float quantity) {
        this.foodQuantity -= quantity;
        if (foodQuantity <= 0) {
            this.getParent().removeActor(this);
            return -foodQuantity;
        } else {
            return 0;
        }
    }


}
