package org.lld.behavioral.vistor.audit;

// The visitor interface defines dedicated visit methods
// for every concrete element class in your object structure.
public interface AssetVisitor {
    void visit(ServerRoom serverRoom);
    void visit(Warehouse warehouse);
}