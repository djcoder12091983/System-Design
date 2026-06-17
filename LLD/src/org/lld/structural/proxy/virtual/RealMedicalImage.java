package org.lld.structural.proxy.virtual;

// real medical image display class
public class RealMedicalImage implements MedicalImage {
    private final String fileName;

    public RealMedicalImage(String fileName) {
        this.fileName = fileName;
        loadFromCloudStorage(); // Network operations happen here
    }

    private void loadFromCloudStorage() {
        System.out.println(">>> [Heavy Network I/O] Downloading 500MB MRI asset: " + fileName);
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {} // Simulated delay
    }

    @Override
    public void displayImage() {
        System.out.println("Rendering crisp high-resolution image scan layer for: " + fileName);
    }
}

