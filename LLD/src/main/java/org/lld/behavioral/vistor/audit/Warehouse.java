package org.lld.behavioral.vistor.audit;

// this is the asset implementation - 2
public class Warehouse implements CorporateAsset {
    private final String location;
    private final double inventoryValue;
    private final boolean storesHazardousMaterials;

    public Warehouse(String location, double value, boolean isHazardous) {
        this.location = location;
        this.inventoryValue = value;
        this.storesHazardousMaterials = isHazardous;
    }

    public double getInventoryValue() { return inventoryValue; }
    public boolean storesHazardousMaterials() { return storesHazardousMaterials; }
    public String getLocation() { return location; }

    @Override
    public void accept(AssetVisitor visitor) {
        visitor.visit(this);
    }
}