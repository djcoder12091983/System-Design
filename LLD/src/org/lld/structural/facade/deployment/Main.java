package org.lld.structural.facade.deployment;

// dummy test class
// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        // Create the single Facade instance
        CloudDeploymentFacade devOpsPipeline = new CloudDeploymentFacade();

        // Client triggers a deployment with a simple single method execution call
        devOpsPipeline.deployApplication(
                "https://github.com",
                "payment-service",
                5
        );

        // Client deploys another application using the exact same simple facade contract
        devOpsPipeline.deployApplication(
                "https://github.com",
                "auth-service",
                2
        );
    }
}
