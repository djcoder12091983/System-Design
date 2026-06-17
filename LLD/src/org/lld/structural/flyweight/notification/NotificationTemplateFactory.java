package org.lld.structural.flyweight.notification;

import java.util.HashMap;
import java.util.Map;

// this class stores notification templates by type so that we can use when needed
// TODO we can think of creating this factory as singleton if we make our factory method as non-static
public class NotificationTemplateFactory {

    // TODO may need to think whether this can be instance variable ot not
    // TODO need to handle thread safety because it can be used by multiple threads
    private static final Map<String, NotificationTemplate> templateRegistry = new HashMap<>();

    public static NotificationTemplate getTemplate(String type) {
        NotificationTemplate template = templateRegistry.get(type);

        if (template == null) {
            // Create and store the heavy object exactly ONCE per type
            template = new ConcreteNotificationTemplate(type);
            templateRegistry.put(type, template);
        }
        return template;
    }

    public static int getRegistrySize() {
        return templateRegistry.size();
    }
}
