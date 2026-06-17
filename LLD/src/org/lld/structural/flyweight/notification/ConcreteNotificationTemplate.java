package org.lld.structural.flyweight.notification;

// Concrete Flyweight (Shared Heavy Layout Asset)
public class ConcreteNotificationTemplate implements NotificationTemplate {
    private final String templateType;      // e.g., "ORDER_CONFIRMATION", "SHIPPING_UPDATE"
    private final String heavyHtmlLayout;    // Simulating heavy structural HTML/CSS formatting string

    public ConcreteNotificationTemplate(String templateType) {
        this.templateType = templateType;

        // Simulating loading heavy layout templates with embedded company logos/CSS assets
        this.heavyHtmlLayout = "<html><head><style>/* Heavy 50KB CSS Bootstrap Framework Layout here */</style></head>"
                + "<body><img src='https://company.com>"
                + "<h1>Welcome to Enterprise Retail</h1>"
                + "<div class='content'>[[DYNAMIC_BODY]]</div>"
                + "<footer>Legal Disclaimers and Unsubscribe boilerplate footer info...</footer></body></html>";

        System.out.println(">>> [System I/O] Loaded and parsed heavy HTML/CSS layout template for: " + templateType);
    }

    @Override
    public void dispatch(String customerEmail, String customerName, String uniqueDetails) {
        // Injecting the light Extrinsic State into our heavy Intrinsic structural layout
        String finalizedEmailBody = heavyHtmlLayout.replace("[[DYNAMIC_BODY]]",
                "Dear " + customerName + ",\n" + uniqueDetails);

        System.out.println("[SMTP Server] Dispatching email to [" + customerEmail + "] using " + templateType + " layout.");
        System.out.println("Rendered Text: " + uniqueDetails);
    }
}