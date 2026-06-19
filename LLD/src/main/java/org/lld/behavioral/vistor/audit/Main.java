package org.lld.behavioral.vistor.audit;

import java.util.ArrayList;
import java.util.List;

// Driver class to test the visitor pattern implementation
// TODO come up with a better test suite using test framework
public class Main {
    public static void main(String[] args) {
        // 1. Setup the collection of corporate physical element nodes
        List<CorporateAsset> companyAssets = new ArrayList<>();
        companyAssets.add(new ServerRoom("Primary-DC-Mumbai", 12, true));
        companyAssets.add(new ServerRoom("Backup-DC-Bengaluru", 4, false));
        companyAssets.add(new Warehouse("Giga-Hub-Delhi", 4500000.0, true));
        companyAssets.add(new Warehouse("Silo-Zone-Chennai", 1200000.0, false));

        // 2. Execute Operation A: Insurance Evaluation Run
        System.out.println("=== EXECUTION LINE: INSURANCE PREMIUM RISK EVALUATION ===");
        InsuranceRiskVisitor insuranceInspector = new InsuranceRiskVisitor();

        for (CorporateAsset asset : companyAssets) {
            asset.accept(insuranceInspector); // Double dispatch mechanism fires here
        }
        System.out.println(">>> Total Portfolio Annual Insurance Premium Cost: ₹" + insuranceInspector.getTotalPremiumQuote());

        // 3. Execute Operation B: Annual Tax Compliance Valuation (Completely different algorithm!)
        System.out.println("\n=== EXECUTION LINE: CORPORATE TAX AUDIT EVALUATION ===");
        TaxAuditVisitor taxAuditor = new TaxAuditVisitor();

        for (CorporateAsset asset : companyAssets) {
            asset.accept(taxAuditor);
        }
        System.out.println(">>> Total System Corporate Tax Write-Off Deductions: ₹" + taxAuditor.getTotalTaxWriteOff());
    }
}
