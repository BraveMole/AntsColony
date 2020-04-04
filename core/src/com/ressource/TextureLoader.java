package com.ressource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {
    public static Texture foodSource;
    public static Texture anthill;
    public static TextureRegion[] workerAntWalking;
    public static TextureRegion[] workerAntEating;

    public static void loadTexture() {
        loadTextureAnts();
        loadTextureEnvironment();
    }

    private static void loadTextureAnts() {
        TextureLoader.workerAntWalking = new TextureRegion[4];
        TextureRegion[][] tempFrames = TextureRegion.split(new Texture("WorkerAntWalking.png"), 25, 37);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                TextureLoader.workerAntWalking[j * 2 + i] = tempFrames[j][i];
            }
        }
        workerAntEating = new TextureRegion[2];
        tempFrames = TextureRegion.split(new Texture("workerAntEating.png"), 25, 37);
        for (int i = 0; i < 2; i++) {
            TextureLoader.workerAntEating[i] = tempFrames[0][i];
        }
    }

    private static void loadTextureEnvironment() {
        foodSource = new Texture("Sugar.png");
        anthill = new Texture("anthill.png");
    }
}
