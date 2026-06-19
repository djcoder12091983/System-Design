package org.lld.structural.flyweight.notification;

// extrinsic state
class NotificationJob {
    // Extrinsic State: Unique data unique to this specific message transaction
    private final String recipientEmail;
    private final String recipientName;
    private final String dynamicDetails;

    // Link reference pointing back to the shared Flyweight layout structural object
    private final NotificationTemplate sharedTemplate;

    public NotificationJob(String email, String name, String details, NotificationTemplate template) {
        this.recipientEmail = email;
        this.recipientName = name;
        this.dynamicDetails = details;
        this.sharedTemplate = template;
    }

    public void executeJob() {
        // Forward the processing down to the shared flyweight template object
        sharedTemplate.dispatch(recipientEmail, recipientName, dynamicDetails);
    }
}
