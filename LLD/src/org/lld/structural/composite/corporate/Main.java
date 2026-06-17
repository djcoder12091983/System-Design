package org.lld.structural.composite.corporate;

// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        // 1. Create individual leaf nodes (Engineers & Recruiters)
        CorporateStructure dev1 = new IndividualContributor("Amit Sharma", "Senior Java Dev", 120000);
        CorporateStructure dev2 = new IndividualContributor("Priya Patel", "QA Engineer", 80000);
        CorporateStructure recruiter = new IndividualContributor("Rohan Das", "Tech Recruiter", 70000);

        // 2. Setup Engineering branch (Composite holding Leaves)
        DepartmentHead vpEngineering = new DepartmentHead("Satish Kumar", "Engineering", 250000);
        vpEngineering.addSubordinate(dev1);
        vpEngineering.addSubordinate(dev2);

        // 3. Setup HR branch (Composite holding a Leaf)
        DepartmentHead hrDirector = new DepartmentHead("Sneha Reddy", "Human Resources", 180000);
        hrDirector.addSubordinate(recruiter);

        // 4. Setup the root corporate entity (Composite holding other Composites)
        DepartmentHead ceo = new DepartmentHead("Vikram Malhotra", "Executive Board", 500000);
        ceo.addSubordinate(vpEngineering);
        ceo.addSubordinate(hrDirector);

        // 5. Execute Uniform Structural Operations
        System.out.println("=== FULL COMPANY STRUCTURE ===");
        ceo.printStructureDetails("");

        System.out.println("\n=== SALARY BUDGET ROLLUP AUDIT ===");
        System.out.println("Total Corporate Burn Rate: ₹" + ceo.calculateTotalSalaryExpense());

        // Demonstrating transparency: Running the exact same call directly on a sub-branch
        System.out.println("Engineering Specific Burn Rate: ₹" + vpEngineering.calculateTotalSalaryExpense());
    }
}
