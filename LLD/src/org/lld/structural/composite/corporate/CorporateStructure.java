package org.lld.structural.composite.corporate;

// defines the behavior of corporate structure
// now we will implement this interface in the concrete classes and use composite DP
// which introduces hierarchies of corporate structures
public interface CorporateStructure {
    void printStructureDetails(String indentation);
    double calculateTotalSalaryExpense();
}
