package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.proxy.virtual.MedicalImage;
import org.lld.structural.proxy.virtual.ProxyMedicalImage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests confirm memory and connection efficiency—asserting that instantiating a proxy metadata block
// is instantaneous and that the real object initialization is strictly deferred until the user triggers
// a method execution.
public class VirtualProxyLazyLoadingTest extends DPTestSuit {

    @Test
    @DisplayName("ProxyMedicalImage instantiation must be instantaneous and skip remote cloud server downloads")
    void testVirtualProxyLazyLoadingInitializationFootprint() {
        // Instantiate the proxy
        MedicalImage proxyScan = new ProxyMedicalImage("Brain_Scan_MRI_2026.dcm");

        String initializationLogs = outputStreamCaptor.toString().trim();

        assertTrue(initializationLogs.contains("Proxy loaded placeholder metadata"),
                "Proxy failed to spin up light metadata placeholders instantly");

        assertFalse(initializationLogs.contains("[Heavy Network I/O] Downloading 500MB"),
                "Performance Defect: Core heavy cloud data was downloaded prematurely before rendering demand");
    }

    @Test
    @DisplayName("Invoking displayImage on the proxy for the first time must trigger lazy initialization on the real target")
    void testVirtualProxyTriggersOnDemandInitializationAndCachesReference() {
        MedicalImage proxyScan = new ProxyMedicalImage("Spine_XRay_2025.dcm");
        outputStreamCaptor.reset(); // Clear constructor setup logs

        // First click/invocation: Should trigger the heavy download
        proxyScan.displayImage();
        String continuousLogs = outputStreamCaptor.toString().trim();

        assertTrue(continuousLogs.contains("[Heavy Network I/O] Downloading 500MB MRI asset"),
                "Proxy failed to execute on-demand initialization workflows upon execution request");
        assertTrue(continuousLogs.contains("Rendering crisp high-resolution image scan layer"),
                "Proxy failed to delegate display operations over to target object");

        // Reset logs to evaluate the subsequent invocation caching efficiency
        outputStreamCaptor.reset();

        // Second click/invocation: Must bypass the download step entirely
        proxyScan.displayImage();
        String secondClickLogs = outputStreamCaptor.toString().trim();

        assertFalse(secondClickLogs.contains("[Heavy Network I/O]"),
                "Caching Error: Proxy refetched heavy asset details from cloud instead of using cached local instance");
        assertTrue(secondClickLogs.contains("Rendering crisp high-resolution image scan layer"),
                "Proxy failed to serve display request on subsequent call graphs");
    }
}
