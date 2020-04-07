package com.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Zone;
import com.process.SearchFood;
import com.process.SearchZone;
import com.ressource.AnimatedSprite;
import com.ressource.TextureLoader;
import org.lwjgl.Sys;

import static com.game.World.mainWorld;

public class FoodSource extends SuperActor {
    private float foodQuantity;
    private final Zone zone;

    public FoodSource(float x, float y, float foodQuantity, Zone zone) {
        this.zone = zone;
        zone.addFoodSource(this);
        this.foodQuantity = foodQuantity;
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
        this.sprite = new AnimatedSprite(TextureLoader.foodSource, x, y, 0, foodQuantity * 4, foodQuantity * 4);
    }

    public FoodSource(float x, float y, float foodQuantity) {
        this(x, y, foodQuantity, SearchZone.searchZone(x, y));
    }


    public float giveFood(float quantity) {
        this.foodQuantity -= quantity;
        this.sprite.setSize(foodQuantity * 4, foodQuantity * 4);
        if (foodQuantity <= 0) {
            foodQuantity = 0;
            if (!zone.removeFoodSource(this)) {
                Gdx.app.log("FoodSource", "Can't remove source of food of the zone in givefood");
            }
            return quantity + foodQuantity;
        } else {
            return quantity;
        }
    }

    public float getFoodQuantity() {
        return foodQuantity;
    }

    public void takeFood(float quantity) {
        this.foodQuantity += quantity;
    }


}
