package org.lld.creational.prototype.game.cache;

import org.lld.creational.prototype.game.ChunkType;
import org.lld.creational.prototype.game.MapChunk;

// this is the contract for chunk registry manager
// we may have different caching strategy manager like inmemory/database + thread safety
public interface ChunkRegistryManager {

    // add chunks by chunk type
    void registerChunk(ChunkType type, MapChunk chunk);

    // obtain chunk by chunk type by cloning
    MapChunk getChunk(ChunkType type);

    // may need to clear cache in case of memory issues
    void clearCache();
}
