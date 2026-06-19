package org.lld.creational.prototype.game;

// this class helps to copy the complex object and also some beahviors like render and all
public interface MapChunk {
    MapChunk makeCopy(); // Strongly-typed alternative to Object.clone()
    void render();
    void setEnemySpawnRate(int rate);
}
