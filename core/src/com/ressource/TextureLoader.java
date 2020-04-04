package com.ressource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {
    public static Texture ants;
    public static Texture foodSource;
    public static Texture anthill;
    public static TextureRegion[] antsCycle;
    public static Animation<TextureRegion> antsWalkingAnimation;

    public static void loadTexture() {
        loadTextureAnts();
        loadTextureEnvironment();
    }

    private static void loadTextureAnts() {
        ants = new Texture("WorkerAntWalking.png");
        antsCycle = new TextureRegion[4];
        TextureRegion[][] tempFrames = TextureRegion.split(ants, 25, 37);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                antsCycle[j * 2 + i] = tempFrames[j][i];
            }
        }
        antsWalkingAnimation = new Animation<>(1f / 4f, antsCycle);
    }

    public static void disposeTextureAnts() {
        ants.dispose();
        foodSource.dispose();
    }

    private static void loadTextureEnvironment() {
        foodSource = new Texture("Sugar.png");
        anthill = new Texture("anthill.png");
    }
}
