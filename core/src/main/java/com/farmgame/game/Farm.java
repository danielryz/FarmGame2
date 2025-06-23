package com.farmgame.game;

import com.badlogic.gdx.math.GridPoint2;
import java.util.HashMap;
import java.util.Map;

public class Farm {
    private final int width;
    private final int height;
    private final Map<GridPoint2, Plot> plots;
    private final int[][] plotPrices;

    private final Map<GridPoint2, AnimalPen> animalPens;
    private final int[][] penPrices;
    private final int penWidth;
    private final int penHeight;

    private final int BASE_PLOT_PRICE = 10;
    private final float MULTIPLIER_PLOT = 1.2f;
    private final float BASE_PEN_PRICE = 100;
    private final float MULTIPLIER_PEN = 1.5f;
    private final int BASE_PEN_CAPACITY = 1;
    private final int PEN_CAPACITY_STEP = 1;

    private DifficultyManager difficultyManager;

    private boolean wateringSystem;

    public Farm(int width, int height, int penWidth, int penHeight) {
        this(width, height, penWidth, penHeight, new DifficultyManager());
    }

    public Farm(int width, int height, int penWidth, int penHeight, DifficultyManager difficultyManager) {
        this.width = width;
        this.height = height;
        this.difficultyManager = difficultyManager;

        this.penWidth = penWidth;
        this.penHeight = penHeight;
        plotPrices = new int[width][height];
        penPrices = new int[penWidth][penHeight];

        this.wateringSystem = false;

        plots = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Plot p = new Plot(difficultyManager);
                plots.put(new GridPoint2(x, y), p);
                plotPrices[x][y] = (int) ( BASE_PLOT_PRICE * MULTIPLIER_PLOT * (x + y));
            }
        }
        plots.get(new GridPoint2(0, 0)).unlock();

        animalPens = new HashMap<>();
        for (int x = 0; x < penWidth; x++) {
            for (int y = 0; y < penHeight; y++) {
                AnimalPen pen = new AnimalPen(x, y, difficultyManager);
                pen.setCapacity(BASE_PEN_CAPACITY);
                animalPens.put(new GridPoint2(x, y), pen);
                penPrices[x][y] = (int) (BASE_PEN_PRICE * MULTIPLIER_PEN * (x + y));
            }
        }
        animalPens.get(new GridPoint2(0, 0)).unlock();
    }

    public Plot getPlot(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return plots.get(new GridPoint2(x, y));
    }

    public int getPlotPrice(int x, int y) {
        return (int)(plotPrices[x][y] / difficultyManager.getMoneyMultiplier());
    }

    public void setDifficultyMultiplier(float multiplier) {
        if (difficultyManager == null) {
            difficultyManager = new DifficultyManager();
        }
        difficultyManager.setDifficultyMultiplier(multiplier);
    }

    public float getDifficultyMultiplier() {
        return difficultyManager.getDifficultyMultiplier();
    }

    public DifficultyManager getDifficultyManager() {
        return difficultyManager;
    }

    public void setDifficultyManager(DifficultyManager difficultyManager) {
        this.difficultyManager = difficultyManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public AnimalPen getAnimalPen(int x, int y) {
        if (x < 0 || x >= penWidth || y < 0 || y >= penHeight) {
            return null;
        }
        return animalPens.get(new GridPoint2(x, y));
    }

    public int getPenPrice(int x, int y) {
        return (int)(penPrices[x][y] / difficultyManager.getMoneyMultiplier());
    }

    public int getPenWidth() {
        return penWidth;
    }

    public int getPenHeight() {
        return penHeight;
    }

    public boolean inPenRange(int x, int y) {
        return (x >= 0 && x < penWidth && y >= 0 && y < penHeight);
    }

    public void upgradePenCapacity(int x, int y) {
        AnimalPen pen = getAnimalPen(x, y);
        if (pen != null) {
            pen.increaseCapacity();
        }
    }

    public boolean hasWateringSystem() {
        return wateringSystem;
    }

    public void setWateringSystem(boolean active) {
        this.wateringSystem = active;
        if (active) {
            for (Plot plot : plots.values()) {
                if (plot != null) plot.setAutoWatered(true);
            }
        }
    }

    public void purchaseWateringSystem() {
        setWateringSystem(true);
    }

    public void addPlot(int x, int y, Plot plot) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        plots.put(new GridPoint2(x, y), plot);
    }

    public void removePlot(int x, int y) {
        plots.remove(new GridPoint2(x, y));
    }

    public void addAnimalPen(int x, int y, AnimalPen pen) {
        if (x < 0 || x >= penWidth || y < 0 || y >= penHeight) return;
        animalPens.put(new GridPoint2(x, y), pen);
    }

    public void removeAnimalPen(int x, int y) {
        animalPens.remove(new GridPoint2(x, y));
    }

    public Map<GridPoint2, Plot> getPlotMap() {
        return plots;
    }

    public Map<GridPoint2, AnimalPen> getAnimalPenMap() {
        return animalPens;
    }


}
