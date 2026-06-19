package org.lld.structural.flyweight.notification;

import java.util.ArrayList;
import java.util.List;

// dummy test class
// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        List<NotificationJob> systemNotificationQueue = new ArrayList<>();

        System.out.println("=== INITIALIZING TRANSACTION NOTIFICATION QUEUE ===");

        // Simulating 5 distinct orders flowing into our microservice event-bus stream

        // Order 1
        NotificationTemplate confirmTemplate = NotificationTemplateFactory.getTemplate("ORDER_CONFIRMATION");
        systemNotificationQueue.add(new NotificationJob("alice@mail.com", "Alice Smith", "Your order #1001 for Laptop is confirmed.", confirmTemplate));

        // Order 2
        systemNotificationQueue.add(new NotificationJob("bob@test.com", "Bob Jones", "Your order #1002 for Smartphone is confirmed.", confirmTemplate));

        // Order 3 (Uses a different template format type)
        NotificationTemplate shippingTemplate = NotificationTemplateFactory.getTemplate("SHIPPING_UPDATE");
        systemNotificationQueue.add(new NotificationJob("charlie@web.com", "Charlie Brown", "Your package #1001 has left Delhi hub via BlueDart.", shippingTemplate));

        // Order 4
        systemNotificationQueue.add(new NotificationJob("dan@mail.com", "Dan White", "Your order #1003 for Headset is confirmed.", confirmTemplate));

        // Order 5
        systemNotificationQueue.add(new NotificationJob("alice@mail.com", "Alice Smith", "Your package #1001 has been delivered.", shippingTemplate));


        System.out.println("\n=== EXECUTION AUDIT ===");
        System.out.println("Total Notification Jobs processing in Memory: " + systemNotificationQueue.size());
        System.out.println("Total Heavy HTML Templates cached in JVM Heap Memory: " + NotificationTemplateFactory.getRegistrySize());

        // Process the queue
        System.out.println("\n=== PROCESSING ALERTS ===");
        for (NotificationJob job : systemNotificationQueue) {
            job.executeJob();
            System.out.println("----------------------------------------------------------------");
        }
    }
}
