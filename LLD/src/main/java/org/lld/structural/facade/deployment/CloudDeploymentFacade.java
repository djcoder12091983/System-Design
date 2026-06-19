package org.lld.structural.facade.deployment;

// facade design which hides all details
public class CloudDeploymentFacade {
    // Composition: Wrapping the complex subsystem components
    private final GitService git;
    private final BuildEngine builder;
    private final DockerService docker;
    private final CloudCluster cloud;

    // The constructor initializes the underlying subsystem graphs automatically
    public CloudDeploymentFacade() {
        this.git = new GitService();
        this.builder = new BuildEngine();
        this.docker = new DockerService();
        this.cloud = new CloudCluster();
    }

    // The simplified facade method hiding all the structural noise
    public void deployApplication(String repoUrl, String appName, int clusterScale) {
        System.out.println("=== FACADE INITIALIZING AUTO-DEPLOYMENT PIPELINE FOR: " + appName.toUpperCase() + " ===");

        // 1. Git steps
        git.pullRepository(repoUrl, "release-production");
        if (!git.checkMergeConflicts()) {
            System.err.println("Deployment Aborted: Merge conflicts detected.");
            return;
        }

        // 2. Build steps
        if (!builder.compileCode() || !builder.executeUnitTests()) {
            System.err.println("Deployment Aborted: Build compilation or tests failed.");
            return;
        }

        // 3. Containerization steps
        String tag = appName + ":v2.6";
        docker.createContainerImage(tag);
        docker.pushToRegistry(tag);

        // 4. Infrastructure & Routing steps
        cloud.provisionComputeNodes(clusterScale);
        cloud.deployRollingUpdate(tag);

        System.out.println("=== FACADE PIPELINE EXECUTION SUCCESSFUL ===\n");
    }
}