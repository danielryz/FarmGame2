package com.farmgame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalDatabase {
    private static final List<AnimalType> animalTypes = new ArrayList<>();

    static {
        animalTypes.add(new AnimalType("Krowa", 200, Set.of("Trawa"), "Mleko", 40f, 40, Color.WHITE, 1, "assets/cow.png"));
    }

    public static List<AnimalType> getAll() {
        return animalTypes;
    }

    public static AnimalType getByName(String name) {
        return animalTypes.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public static List<String> getByLevel(int level) {
        return animalTypes.stream()
            .filter(a -> a.getRequiredLevel() == level)
            .map(AnimalType::getName)
            .collect(Collectors.toList());
    }
}
