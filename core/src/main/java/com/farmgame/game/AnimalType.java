package com.farmgame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import java.util.Set;

public class AnimalType {
    private final String name;
    private final int cost;
    private final Set<String> feedSet;
    private final String productName;
    private final float productTime;
    private final int sellPrice;
    private final Color color;
    private final int requiredLevel;
    private final Texture texture;

    public AnimalType(String name, int cost, Set<String> feedSet, String productName, float productTime, int sellPrice, Color color, int requiredLevel) {
        this(name, cost, feedSet, productName, productTime, sellPrice, color, requiredLevel, null);
    }

    public AnimalType(String name, int cost, Set<String> feedSet, String productName, float productTime, int sellPrice, Color color, int requiredLevel, String texturePath) {
        this.name = name;
        this.cost = cost;
        this.feedSet = feedSet;
        this.productName = productName;
        this.productTime = productTime;
        this.sellPrice = sellPrice;
        this.color = color;
        this.requiredLevel = requiredLevel;
        this.texture = texturePath != null ? new Texture(Gdx.files.internal(texturePath)) : null;
    }
    public String getName() {
        return name;
    }
    public static AnimalType getByName(String name) {
        return AnimalDatabase.getByName(name);
    }
    public int getCost() {
        return cost;
    }
    public Set<String> getFeedSet() {
        return feedSet;
    }
    public String getProductName() {
        return productName;
    }
    public float getProductTime() {
        return productTime;
    }
    public int getSellPrice() {
        return sellPrice;
    }
    public Color getColor() {
        return color;
    }
    public Texture getTexture() { return texture; }
    public int getRequiredLevel() {
        return requiredLevel;
    }
}
