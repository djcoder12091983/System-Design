package org.lld.behavioral.vistor.audit;

// Operation 2: Evaluating Annual Corporate Tax Write-Offs and Depreciation Value
public class TaxAuditVisitor implements AssetVisitor {
    private double totalTaxWriteOff = 0.0;

    @Override
    public void visit(ServerRoom serverRoom) {
        // Servers are high-depreciation tech assets, yielding 30% tax deductions
        double deduction = (serverRoom.getServerRackCount() * 120000.0) * 0.30;
        totalTaxWriteOff += deduction;
        System.out.println("[Tax Audit] Tech Depreciation Deduction for " + serverRoom.getRoomIdentifier() + ": ₹" + deduction);
    }

    @Override
    public void visit(Warehouse warehouse) {
        // Real estate infrastructure yields a smaller flat 5% deduction
        double deduction = warehouse.getInventoryValue() * 0.05;
        totalTaxWriteOff += deduction;
        System.out.println("[Tax Audit] Facility Write-Off Deduction for " + warehouse.getLocation() + ": ₹" + deduction);
    }

    public double getTotalTaxWriteOff() { return totalTaxWriteOff; }
}