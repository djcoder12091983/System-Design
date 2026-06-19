package org.lld.structural.proxy.virtual;

// dummy test class
// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        System.out.println("=== INITIALIZING PATIENT DOSSIER CHART ===");
        MedicalImage scanA = new ProxyMedicalImage("Brain_Scan_MRI_2026.dcm");
        MedicalImage scanB = new ProxyMedicalImage("Spine_XRay_2025.dcm");
        System.out.println("=== DASHBOARD CHROME LOADED INSTANTLY ===\n");

        // The user goes through the file list and clicks only on Scan B
        System.out.println("--- User clicks on 'View Spine X-Ray' ---");
        scanB.displayImage(); // Heavy initialization triggers now

        System.out.println("\n--- User clicks on 'View Spine X-Ray' again ---");
        scanB.displayImage(); // Fast execution: Already cached inside proxy instance!
    }
}
