package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.facade.deployment.CloudDeploymentFacade;

import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests ensure that the Facade successfully hides the chaotic configuration details of individual
// subsystem services, acting as a simple unified endpoint orchestrator.
@DisplayName("Facade Pattern - Cloud DevOps Automation Pipeline Test Suite")
public class DeploymentFacadeTest extends DPTestSuit {

    @Test
    @DisplayName("Facade deployment method should smoothly orchestrate the complete Git, Build, Docker, and AWS subsystem loop")
    void testFacadeOrchestrationWorkflow() {
        CloudDeploymentFacade devOpsEngine = new CloudDeploymentFacade();

        // Single clean high-level execution step trigger call
        devOpsEngine.deployApplication("https://github.com", "auth-service", 3);

        String structuralTrackingLogs = outputStreamCaptor.toString().trim();

        // Assert Git subsystem step executed successfully
        assertTrue(structuralTrackingLogs.contains("[Git] Cloning repository: https://github.com [release-production]..."));

        // Assert Maven compiling build step executed successfully
        assertTrue(structuralTrackingLogs.contains("[Build] Executing 142 unit tests... All Tests Passed."));

        // Assert Container generation infrastructure steps completed
        assertTrue(structuralTrackingLogs.contains("[Docker] Pushing image tag 'auth-service:v2.6' to cloud container registry..."));

        // Assert final infrastructure routing operations finalized via Facade
        assertTrue(structuralTrackingLogs.contains("[AWS] Scaling compute tier clusters to 3 nodes."));
        assertTrue(structuralTrackingLogs.contains("=== FACADE PIPELINE EXECUTION SUCCESSFUL ==="));
    }
}
