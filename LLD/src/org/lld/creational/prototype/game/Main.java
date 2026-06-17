package org.lld.creational.prototype.game;

import org.lld.creational.prototype.game.cache.ChunkRegistry;

// this is for dummy testing
// TODO we need think of coming up with proper test cases
public class Main {
    public static void main(String[] args) {
        // 1. Player enters Zone A - Spawn a standard Rainy Forest Chunk
        System.out.println("--- Spawning Chunk 1 ---");
        MapChunk chunk1 = ChunkRegistry.getChunk(ChunkType.RAINY_FOREST);
        chunk1.render(); // Prints instantly

        // 2. Player enters Zone B (Boss Fight) - Spawn another Rainy Forest but modify it
        System.out.println("\n--- Spawning Chunk 2 (Boss Area) ---");
        MapChunk chunk2 = ChunkRegistry.getChunk(ChunkType.RAINY_FOREST);
        chunk2.setEnemySpawnRate(85); // Dynamically customize the clone
        chunk2.render(); // Prints instantly

        // 3. Player enters Zone C - Spawn a Sunny Forest
        System.out.println("\n--- Spawning Chunk 3 ---");
        MapChunk chunk3 = ChunkRegistry.getChunk(ChunkType.SUNNY_FOREST);
        chunk3.render();

        // Verification: Check that modifying chunk2 didn't break chunk1
        System.out.println("\n--- Verification ---");
        chunk1.render(); // Still retains its default 10% spawn rate
    }
}
