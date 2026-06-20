package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.flyweight.notification.NotificationJob;
import org.lld.structural.flyweight.notification.NotificationTemplate;
import org.lld.structural.flyweight.notification.NotificationTemplateFactory;

import static org.junit.jupiter.api.Assertions.*;

// These tests prove resource and heap minimization: identical intrinsic models must be aggressively reused
// inside the system cache, while dynamic extrinsic transaction data stays perfectly isolated across individual
// tracking jobs.
@DisplayName("Flyweight Pattern - Enterprise Alert Template Manager Test Suite")
public class NotificationFlyweightTest extends DPTestSuit {

    @Test
    @DisplayName("The Factory Registry must reuse a single shared Intrinsic asset mapping across identical types")
    void testFlyweightFactoryCachingAndReusability() {
        // Pull identical notification layout template profiles multiple times
        NotificationTemplate templateInstance1 = NotificationTemplateFactory.getTemplate("ORDER_CONFIRMATION");
        NotificationTemplate templateInstance2 = NotificationTemplateFactory.getTemplate("ORDER_CONFIRMATION");

        NotificationTemplate distinctTemplate = NotificationTemplateFactory.getTemplate("SHIPPING_UPDATE");

        assertNotNull(templateInstance1);
        // Verify structural object identity reference caching reuse parameters
        assertSame(templateInstance1, templateInstance2, "Flyweight factory must deliver the exact same cached object mapping reference");
        assertNotSame(templateInstance1, distinctTemplate, "Different types must generate separate model layouts inside registry map");

        assertEquals(2, NotificationTemplateFactory.getRegistrySize(), "JVM Heap memory allocation footprints should strictly equal unique type categories");
    }

    @Test
    @DisplayName("NotificationJobs holding Extrinsic customer states must process data uniquely without mutating the shared template metadata")
    void testExtrinsicStateIsolationAcrossNotificationJobs() {
        NotificationTemplate baseFlyweight = NotificationTemplateFactory.getTemplate("ORDER_CONFIRMATION");

        // Set up two distinct processing jobs holding completely different unique parameters
        NotificationJob job1 = new NotificationJob("alice@mail.com", "Alice Smith", "Order #1001 confirmed", baseFlyweight);
        NotificationJob job2 = new NotificationJob("bob@test.com", "Bob Jones", "Order #1002 confirmed", baseFlyweight);

        // Process job 1 details
        job1.executeJob();
        String outJob1 = outputStreamCaptor.toString().trim();
        outputStreamCaptor.reset(); // Reset interceptor buffers

        // Process job 2 details
        job2.executeJob();
        String outJob2 = outputStreamCaptor.toString().trim();

        // Assert job 1 executed parameters accurately
        assertTrue(outJob1.contains("Dispatching email to [alice@mail.com]"));
        assertTrue(outJob1.contains("Dear Alice Smith"));

        // Assert job 2 execution stayed completely isolated from job 1 state attributes
        assertTrue(outJob2.contains("Dispatching email to [bob@test.com]"));
        assertTrue(outJob2.contains("Dear Bob Jones"));
        assertFalse(outJob2.contains("Alice Smith"), "Extrinsic state pollution leak tracking error located across shared flyweight objects");
    }
}
