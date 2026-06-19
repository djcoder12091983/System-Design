package org.lld.creational.prototype.game;

import java.util.ArrayList;
import java.util.List;

// this class will have forest properties and rendering part and all
public class ForestChunk implements MapChunk {
    private List<String> heavyTextures; // Heavy resource shared across clones
    private int enemySpawnRate;          // Variable state custom to each clone
    private String weatherEffect;

    // Heavy constructor simulating I/O or procedural generation
    public ForestChunk(String weatherEffect) {
        this.weatherEffect = weatherEffect;
        this.heavyTextures = new ArrayList<>();

        System.out.println(">>> Heavy Operation: Loading 4K Forest Textures from Disk...");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {} // Simulate delay
        this.heavyTextures.add("Grass_Texture_HD.png");
        this.heavyTextures.add("Oak_Tree_Mesh.obj");

        this.enemySpawnRate = 10; // Default
    }

    // Clone Constructor for Prototype deep/shallow copying
    private ForestChunk(ForestChunk source) {
        // Shallow copy is safe for immutable assets like textures
        this.heavyTextures = source.heavyTextures;
        this.weatherEffect = source.weatherEffect;
        this.enemySpawnRate = source.enemySpawnRate;
    }

    @Override
    public void setEnemySpawnRate(int rate) {
        this.enemySpawnRate = rate;
    }

    @Override
    public MapChunk makeCopy() {
        // Creates a copy instantly without invoking the slow disk/network operations
        return new ForestChunk(this);
    }

    @Override
    public void render() {
        System.out.println("Rendering Forest [" + weatherEffect + "] with spawn rate: "
                + enemySpawnRate + "% using textures: " + heavyTextures);
    }
}
