package org.lld.structural.bridge.message;

// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        // Platform configurations (Implementations)
        MessageSender smsPlatform = new TwilioSmsSender();
        MessageSender emailPlatform = new SendGridEmailSender();
        MessageSender slackPlatform = new SlackWebhookSender();

        // 1. Scenario: Server room is overheating! Send an Urgent Message via SMS.
        Message criticalAlert = new UrgentMessage("Server Rack 4 overheating (92°C)", smsPlatform);
        criticalAlert.logAndDeliver("+15550192");

        // 2. Scenario: Server room is still overheating! Pivot to Slack instantly using the SAME message object logic.
        Message criticalAlertSlack = new UrgentMessage("Server Rack 4 overheating (92°C)", slackPlatform);
        criticalAlertSlack.logAndDeliver("ops-alerts");

        // 3. Scenario: End-of-month promotional run sent via Email
        Message blackFridayPromo = new MarketingMessage("Get 50% off all cloud subscriptions today!", emailPlatform);
        blackFridayPromo.logAndDeliver("customer_vip@company.com");
    }
}
