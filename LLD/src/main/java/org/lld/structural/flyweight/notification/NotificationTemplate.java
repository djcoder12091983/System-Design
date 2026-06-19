package org.lld.structural.flyweight.notification;

// this class targets to create shared notification templates which is heavy to create
// so we will attach with some templates where it's already created by some type
public interface NotificationTemplate {
    // Accepts unique Extrinsic state parameters from the client context
    void dispatch(String customerEmail, String customerName, String uniqueDetails);
}
