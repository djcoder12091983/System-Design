package org.lld.behavioral.vistor.audit;

// Operation 1: Evaluating Insurance Premium Risk Profiles
public class InsuranceRiskVisitor implements AssetVisitor {
    private double totalPremiumQuote = 0.0;

    @Override
    public void visit(ServerRoom serverRoom) {
        // Base risk calculated per server rack
        double riskBase = serverRoom.getServerRackCount() * 5000.0;

        // High-tech gas protection lowers insurance liability risk premium
        if (!serverRoom.hasHalonGasFireSuppression()) {
            riskBase *= 2.5; // Premium spikes if fire risk is unmitigated
        }

        totalPremiumQuote += riskBase;
        System.out.println("[Insurance Analysis] Evaluated Server Room " + serverRoom.getRoomIdentifier() + ". Premium Risk: ₹" + riskBase);
    }

    @Override
    public void visit(Warehouse warehouse) {
        double riskBase = warehouse.getInventoryValue() * 0.02; // 2% baseline coverage cost

        if (warehouse.storesHazardousMaterials()) {
            riskBase += 150000.0; // Flat liability surcharge
        }

        totalPremiumQuote += riskBase;
        System.out.println("[Insurance Analysis] Evaluated Warehouse in " + warehouse.getLocation() + ". Premium Risk: ₹" + riskBase);
    }

    public double getTotalPremiumQuote() { return totalPremiumQuote; }
}
