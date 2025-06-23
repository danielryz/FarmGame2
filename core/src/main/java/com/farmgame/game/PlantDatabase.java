package com.farmgame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;

import java.util.*;
import java.util.stream.Collectors;

public class PlantDatabase {
    private static final List<PlantType> plantTypes = new ArrayList<>();

    static {
        plantTypes.add(new PlantType("Trawa", 15f, 10, 12, 1, "assets/clover.png"));
    }

    public static List<PlantType> getAll() {
        return plantTypes;
    }

    public static PlantType getByName(String name) {
        return plantTypes.stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public static List<String> getByLevel(int level) {
        return plantTypes.stream()
            .filter(a -> a.getRequiredLevel() == level)
            .map(PlantType::getName)
            .collect(Collectors.toList());
    }
}
