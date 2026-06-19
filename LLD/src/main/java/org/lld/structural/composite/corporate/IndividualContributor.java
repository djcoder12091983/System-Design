package org.lld.structural.composite.corporate;

// leaf node (junior entities) structure
public class IndividualContributor implements CorporateStructure {
    private final String name;
    private final String role;
    private final double salary;

    public IndividualContributor(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    @Override
    public void printStructureDetails(String indentation) {
        System.out.println(indentation + "- " + name + " [" + role + "] (Salary: ₹" + salary + ")");
    }

    @Override
    public double calculateTotalSalaryExpense() {
        return this.salary; // Returns just their individual salary
    }
}
