package org.lld.structural.facade.deployment;

// Subsystem Component 3
public class DockerService {
    public void createContainerImage(String imageName) {
        System.out.println("[Docker] Building container snapshot: " + imageName);
    }
    public void pushToRegistry(String imageTag) {
        System.out.println("[Docker] Pushing image tag '" + imageTag + "' to cloud container registry...");
    }
}
