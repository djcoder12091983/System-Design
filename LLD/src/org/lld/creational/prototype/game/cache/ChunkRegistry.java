package org.lld.creational.prototype.game.cache;

import org.lld.creational.prototype.game.ChunkType;
import org.lld.creational.prototype.game.ForestChunk;
import org.lld.creational.prototype.game.MapChunk;

import java.util.HashMap;
import java.util.Map;

public class ChunkRegistry {
    // TODO we can think of making this thread safe also need to think whether this cache
    // can we put it at instance level or what i mean class level is required or what
    private static final Map<ChunkType, MapChunk> prototypes = new HashMap<>();

    // Load prototypes into memory once during initialization
    // TODO here we are prepopulating some objects we can think of adding it from applicaiton
    // this is testing purpose
    static {
        System.out.println("=== INITIALIZING GAME ENGINE STATE ===");
        prototypes.put(ChunkType.RAINY_FOREST, new ForestChunk("Rainy"));
        prototypes.put(ChunkType.SUNNY_FOREST, new ForestChunk("Sunny"));
        System.out.println("=== INITIALIZATION COMPLETE ===\n");
    }

    // Factory-style access to the prototypes
    public static MapChunk getChunk(ChunkType type) {
        MapChunk prototype = prototypes.get(type);
        if (prototype == null) {
            throw new IllegalArgumentException("Unknown chunk type");
        }
        return prototype.makeCopy(); // Return a fresh copy of the cached prototype
    }
}
