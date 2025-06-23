package com.farmgame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.farmgame.game.Plant.GrowthStage;

public class PlantType {
    private final String name;
    private final float growthTime;
    private final Color color;
    private final int seedPrice;
    private final int sellPrice;
    private final int requiredLevel;
    private final TextureRegion[] stageRegions;

    public PlantType(String name, float growthTime, Color color, int seedPrice, int sellPrice, int requiredLevel) {
        this.name = name;
        this.growthTime = growthTime;
        this.color = color;
        this.seedPrice = seedPrice;
        this.sellPrice = sellPrice;
        this.requiredLevel = requiredLevel;
        this.stageRegions = generateStageTextures(color);
    }

    public String getName() {
        return name;
    }

    public float getGrowthTime() {
        return growthTime;
    }

    public Color getColor() {
        return color;
    }

    public int getSeedPrice() {
        return seedPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public static PlantType getByName(String name) {
        return PlantDatabase.getByName(name);
    }

    private TextureRegion[] generateStageTextures(Color base) {
        int size = 16;
        Pixmap pixmap = new Pixmap(size * 3, size, Pixmap.Format.RGBA8888);
        for (int i = 0; i < 3; i++) {
            float brightness = 0.6f + 0.2f * i;
            Color c = new Color(base);
            c.mul(brightness);
            pixmap.setColor(c);
            pixmap.fillRectangle(i * size, 0, size, size);
        }
        Texture texture = new Texture(pixmap);
        TextureRegion[] regions = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            regions[i] = new TextureRegion(texture, i * size, 0, size, size);
        }
        pixmap.dispose();
        return regions;
    }

    public TextureRegion getTextureForStage(Plant.GrowthStage stage) {
        return switch (stage) {
            case PLANTED -> stageRegions[0];
            case GROWING -> stageRegions[1];
            case READY -> stageRegions[2];
        };
    }

    @Override
    public String toString() {
        return name;
    }
}
