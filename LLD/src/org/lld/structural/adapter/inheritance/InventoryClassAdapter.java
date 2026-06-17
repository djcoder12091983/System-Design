package org.lld.structural.adapter.inheritance;

// adaptation using inheritance
public class InventoryClassAdapter extends LegacyWarehouseSystem implements ModernInventory {

    @Override
    public int getAvailableStockCount() {
        // Inherits legacy methods directly and applies the translation math
        return getTotalPalletsInStorage() * getItemsPerPallet();
    }
}
