package org.lld.behavioral.vistor.audit;

// this is the asset implementation - 1
public class ServerRoom implements CorporateAsset {
    private final String roomIdentifier;
    private final int serverRackCount;
    private final boolean hasHalonGasFireSuppression;

    public ServerRoom(String id, int racks, boolean hasHalon) {
        this.roomIdentifier = id;
        this.serverRackCount = racks;
        this.hasHalonGasFireSuppression = hasHalon;
    }

    public int getServerRackCount() { return serverRackCount; }
    public boolean hasHalonGasFireSuppression() { return hasHalonGasFireSuppression; }
    public String getRoomIdentifier() { return roomIdentifier; }

    @Override
    public void accept(AssetVisitor visitor) {
        // Double Dispatch: Passes 'this' concrete type back to the visitor at runtime
        visitor.visit(this);
    }
}
