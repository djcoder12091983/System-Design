package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.vistor.audit.InsuranceRiskVisitor;
import org.lld.behavioral.vistor.audit.ServerRoom;
import org.lld.behavioral.vistor.audit.TaxAuditVisitor;
import org.lld.behavioral.vistor.audit.Warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;

// These tests confirm double-dispatch routing and external algorithmic isolation.
// They verify that adding calculation features via visitor instances executes correct overloaded class routing
// paths (ServerRoom vs. Warehouse) without requiring changes to foundational asset properties.
@DisplayName("Visitor Pattern - Infrastructure Asset Risk Auditor Test Suite")
public class AuditVistorTest extends DPTestSuit {

    private ServerRoom safeServerRoom;
    private ServerRoom riskyServerRoom;
    private Warehouse hazardousWarehouse;
    private Warehouse standardWarehouse;

    @BeforeEach
    void initCorporateAssets() {
        // Build stable elements
        safeServerRoom = new ServerRoom("DC-Mumbai", 10, true);   // 10 racks, gas fire system active
        riskyServerRoom = new ServerRoom("DC-Local", 2, false);   // 2 racks, no gas system (High risk)
        hazardousWarehouse = new Warehouse("Hub-Delhi", 1000000.0, true);  // ₹10 Lakh value, hazardous items
        standardWarehouse = new Warehouse("Silo-Goa", 500000.0, false);     // ₹5 Lakh value, normal items
    }

    @Test
    @DisplayName("InsuranceRiskVisitor must process custom calculations based on target element risk properties")
    void testInsuranceRiskVisitorDoubleDispatchMath() {
        InsuranceRiskVisitor insuranceInspector = new InsuranceRiskVisitor();

        // 1. Visit Safe Server Room: 10 racks * 5000 = ₹50,000 baseline premium cost
        safeServerRoom.accept(insuranceInspector);
        assertEquals(50000.0, insuranceInspector.getTotalPremiumQuote(), 0.001);

        // 2. Visit Risky Server Room: 2 racks * 5000 = 10,000 * 2.5 risk penalty multiplier = ₹25,000
        riskyServerRoom.accept(insuranceInspector);
        assertEquals(75000.0, insuranceInspector.getTotalPremiumQuote(), 0.001); // Cumulative total: 50k + 25k

        // 3. Visit Hazardous Warehouse: (1,000,000 * 0.02) = 20,000 + 150,000 flat hazard fee = ₹170,000
        hazardousWarehouse.accept(insuranceInspector);
        assertEquals(245000.0, insuranceInspector.getTotalPremiumQuote(), 0.001); // Cumulative total: 75k + 170k
    }

    @Test
    @DisplayName("TaxAuditVisitor must run an entirely different asset depreciation deduction algorithm across the same nodes")
    void testTaxAuditVisitorAssetDepreciationMath() {
        TaxAuditVisitor taxAuditor = new TaxAuditVisitor();

        // 1. Visit Safe Server Room: (10 racks * 120,000 cost) = 1,200,000 * 30% tech depreciation deduction = ₹360,000
        safeServerRoom.accept(taxAuditor);
        assertEquals(360000.0, taxAuditor.getTotalTaxWriteOff(), 0.001);

        // 2. Visit Standard Warehouse: ₹500,000 value * 5% real estate structural deduction = ₹25,000
        standardWarehouse.accept(taxAuditor);
        assertEquals(385000.0, taxAuditor.getTotalTaxWriteOff(), 0.001); // Cumulative total: 360k + 25k
    }
}
