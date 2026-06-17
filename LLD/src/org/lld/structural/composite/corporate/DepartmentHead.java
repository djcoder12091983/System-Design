package org.lld.structural.composite.corporate;

import java.util.ArrayList;
import java.util.List;

// intermediate nodes which have some parent and children
public class DepartmentHead implements CorporateStructure {
    private final String name;
    private final String departmentName;
    private final double operationalBonus;

    // The key composite trait: Storing references to the uniform abstraction layer
    private final List<CorporateStructure> subordinates = new ArrayList<>();

    public DepartmentHead(String name, String departmentName, double operationalBonus) {
        this.name = name;
        this.departmentName = departmentName;
        this.operationalBonus = operationalBonus;
    }

    // Management controls to update the composite hierarchy tree structure
    public void addSubordinate(CorporateStructure employee) {
        subordinates.add(employee);
    }

    public void removeSubordinate(CorporateStructure employee) {
        subordinates.remove(employee);
    }

    @Override
    public void printStructureDetails(String indentation) {
        System.out.println(indentation + "+ Head: " + name + " [Dept: " + departmentName + "]");

        // Delegate structural printing recursively down to all children
        for (CorporateStructure subordinate : subordinates) {
            subordinate.printStructureDetails(indentation + "   ");
        }
    }

    @Override
    public double calculateTotalSalaryExpense() {
        // Start with the head's own cost overhead
        double totalExpense = this.operationalBonus;

        // Recursively accumulate the salary costs of the entire tree underneath them
        for (CorporateStructure subordinate : subordinates) {
            totalExpense += subordinate.calculateTotalSalaryExpense();
        }
        return totalExpense;
    }
}