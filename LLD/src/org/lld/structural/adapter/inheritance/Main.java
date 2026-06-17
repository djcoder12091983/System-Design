package org.lld.structural.adapter.inheritance;

// main class
// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        // The adapter IS a ModernInventory instance through inheritance
        ModernInventory inventoryTracker = new InventoryClassAdapter();

        System.out.println("Dashboard Inventory Count: " + inventoryTracker.getAvailableStockCount());
    }
}
