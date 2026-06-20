package org.lld.structural;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.composite.corporate.CorporateStructure;
import org.lld.structural.composite.corporate.DepartmentHead;
import org.lld.structural.composite.corporate.IndividualContributor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests ensure transparency and uniform handling: the system must process individual nodes (leaves) and
// organizational branches (composites) using identical method contracts, executing recursive roll-up math calculations properly.
@DisplayName("Composite Pattern - Corporate Tree Hierarchy Test Suite")
public class CorporateCompositeTest extends DPTestSuit {

    private CorporateStructure engineerLeaf1;
    private CorporateStructure engineerLeaf2;
    private DepartmentHead engineeringSubBranch;
    private DepartmentHead corporateRootCeo;

    // data setup
    @BeforeEach
    void buildCorporateStructureTree() {
        // Initialize Leaves
        engineerLeaf1 = new IndividualContributor("Amit Sharma", "Senior Dev", 120000.0);
        engineerLeaf2 = new IndividualContributor("Priya Patel", "QA Lead", 80000.0);

        // Initialize Sub-Composite Department
        engineeringSubBranch = new DepartmentHead("Satish Kumar", "Engineering", 250000.0);
        engineeringSubBranch.addSubordinate(engineerLeaf1);
        engineeringSubBranch.addSubordinate(engineerLeaf2);

        // Initialize Main Tree Root Composite
        corporateRootCeo = new DepartmentHead("Vikram Malhotra", "Executive Board", 500000.0);
        corporateRootCeo.addSubordinate(engineeringSubBranch);
    }

    @Test
    @DisplayName("A Leaf node must return only its own private financial salary constraints")
    void testLeafNodeSalaryCalculation() {
        double baselineCost = engineerLeaf1.calculateTotalSalaryExpense();
        assertEquals(120000.0, baselineCost, 0.001, "Individual contributor rollup math must reflect flat item salary");
    }

    @Test
    @DisplayName("A Composite branch department must recursively accumulate salaries of all child nodes beneath it")
    void testSubBranchCompositeDepartmentRollupCalculation() {
        // Math check: Sub-branch cost = Satish Bonus (250,000) + Amit (120,000) + Priya (80,000) = 450,000
        double totalDeptCost = engineeringSubBranch.calculateTotalSalaryExpense();
        assertEquals(450000.0, totalDeptCost, 0.001, "Department roll-up aggregation failed to accumulate structural tree values");
    }

    @Test
    @DisplayName("The top Root node must transparently trigger recursive calculations down through nested child sub-composites")
    void testFullTreeCorporateRollupCalculation() {
        // Math check: Root CEO (500,000) + Engineering Sub-Branch Total (450,000) = 950,000
        double globalCorporateBurnRate = corporateRootCeo.calculateTotalSalaryExpense();
        assertEquals(950000.0, globalCorporateBurnRate, 0.001, "Tree structural traversal calculation breakdown occurred");
    }

    @Test
    @DisplayName("Executing structural detailing prints on a Composite node must recursively visit all attached leaf elements")
    void testRecursiveStructureVisualDetailsRendering() {
        corporateRootCeo.printStructureDetails("");

        String structuralLayoutOutput = outputStreamCaptor.toString();
        assertTrue(structuralLayoutOutput.contains("+ Head: Vikram Malhotra [Dept: Executive Board]"), "Tree root missing from dump");
        assertTrue(structuralLayoutOutput.contains("+ Head: Satish Kumar [Dept: Engineering]"), "Internal nested composite branch missing from dump");
        assertTrue(structuralLayoutOutput.contains("- Amit Sharma [Senior Dev]"), "Leaf node missing from deep structural tree dump");
    }
}

