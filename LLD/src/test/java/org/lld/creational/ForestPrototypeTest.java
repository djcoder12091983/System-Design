package org.lld.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.creational.prototype.game.ChunkType;
import org.lld.creational.prototype.game.MapChunk;
import org.lld.creational.prototype.game.cache.ChunkRegistry;

import static org.junit.jupiter.api.Assertions.*;

// forest prototype DP test cases
@DisplayName("Prototype Pattern - Map Chunk Test Suite")
public class ForestPrototypeTest extends DPTestSuit {

    @Test
    @DisplayName("Cloned chunks must maintain separate identity but copy default configuration values")
    void testPrototypeCloningIntegrity() {
        // Fetch a fresh clone from the pre-cached Registry templates
        MapChunk chunk1 = ChunkRegistry.getChunk(ChunkType.RAINY_FOREST);
        MapChunk chunk2 = ChunkRegistry.getChunk(ChunkType.RAINY_FOREST);

        assertNotNull(chunk1);
        assertNotNull(chunk2);
        assertNotSame(chunk1, chunk2, "Cloned instances must occupy separate memory memory addresses");
    }

    @Test
    @DisplayName("Modifying state mutations on a clone must never affect separate clone instances or the registry templates")
    void testStateIsolationBetweenClones() {
        MapChunk baselineChunk = ChunkRegistry.getChunk(ChunkType.RAINY_FOREST);
        MapChunk bossZoneChunk = ChunkRegistry.getChunk(ChunkType.RAINY_FOREST);

        // Customize ONLY the boss zone clone instance state
        bossZoneChunk.setEnemySpawnRate(85);

        // Render baseline clone to parse console output variables
        baselineChunk.render();
        String baselineOutput = outputStreamCaptor.toString();
        outputStreamCaptor.reset(); // Reset stream interceptor buffer

        // Render custom boss clone
        bossZoneChunk.render();
        String bossOutput = outputStreamCaptor.toString();

        assertTrue(baselineOutput.contains("spawn rate: 10%"), "Baseline clone must retain default 10% rate");
        assertTrue(bossOutput.contains("spawn rate: 85%"), "Custom clone must accurately accept the override modification");
    }

    @Test
    @DisplayName("Instantiating clones from the Registry must skip heavy disk I/O operational logs")
    void testPerformanceSkippingHeavyConstructor() {
        // Clearing logs created during class loading initial setup
        outputStreamCaptor.reset();

        MapChunk fastClone = ChunkRegistry.getChunk(ChunkType.SUNNY_FOREST);
        fastClone.render();

        String executionLog = outputStreamCaptor.toString();
        assertFalse(executionLog.contains("Heavy Operation: Loading 4K Forest Textures"),
                "Cloning must skip expensive file stream reads completely");
    }
}
