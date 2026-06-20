package org.lld.structural.adapter.inheritance;

// legacy class which we will adapt
public class LegacyWarehouseSystem {
    // TODO these values we may need to read from configurations
    private final int totalPallets = 45;
    private final int itemsPerPallet = 20;

    public int getTotalPalletsInStorage() {
        return totalPallets;
    }

    public int getItemsPerPallet() {
        return itemsPerPallet;
    }
}
