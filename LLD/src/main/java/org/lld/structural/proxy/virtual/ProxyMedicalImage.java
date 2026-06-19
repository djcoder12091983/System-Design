package org.lld.structural.proxy.virtual;

// this proxy class is used to display some heavy image display placeholder initially
// when image is clicked then it delegates to actual image display
// also takes care of loading same image from memory if already loaded to save network calls
public class ProxyMedicalImage implements MedicalImage {
    private final String fileName;

    // The real target object stays uninstantiated (null) initially
    private RealMedicalImage realImageInstance;

    public ProxyMedicalImage(String fileName) {
        this.fileName = fileName;
        // Instantiating this proxy is instantaneous because the cloud load is skipped
        System.out.println("Proxy loaded placeholder metadata for: " + fileName);
    }

    @Override
    public void displayImage() {
        // Lazy Loading check: Initialize the real object on the first method invocation
        if (realImageInstance == null) {
            realImageInstance = new RealMedicalImage(fileName);
        }

        // Forward the actual execution call
        realImageInstance.displayImage();
    }
}
