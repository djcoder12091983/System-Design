package org.lld.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.creational.builder.object.UserAccount;

import static org.junit.jupiter.api.Assertions.*;

// These tests ensure our fluent parameters avoid telescoping clutter, properly assign optional default
// configurations, and execute runtime safety validation checks during object generation.
@DisplayName("Modern Fluent Builder - UserAccount Test Suite")
public class UserAccountFluentBuilderTest {

    @Test
    @DisplayName("Building a profile with minimum required fields should successfully inject optional defaults")
    void testBuilderMinimumInputsAndDefaults() {
        UserAccount account = new UserAccount.Builder("johndoe", "john@example.com").build();

        assertNotNull(account);
        // Using toString check or accessors depending on layout availability
        String accountDetails = account.toString();
        assertTrue(accountDetails.contains("username='johndoe'"));
        assertTrue(accountDetails.contains("email='john@example.com'"));
        assertTrue(accountDetails.contains("isPremium=false"), "Should automatically default to standard non-premium");
        assertTrue(accountDetails.contains("profilePic=default.png"), "Should apply base avatar fallback configuration string");
    }

    @Test
    @DisplayName("Chaining structural mutations should properly bind custom optional values to immutable fields")
    void testFluentChainingConfiguration() {
        UserAccount proAccount = new UserAccount.Builder("alex_pro", "alex@biz.com")
                .isPremium(true)
                .profilePicUrl("premium-avatar.png")
                .twoFactorEnabled(true)
                .build();

        String proDetails = proAccount.toString();
        assertTrue(proDetails.contains("username='alex_pro'"));
        assertTrue(proDetails.contains("isPremium=true"));
        assertTrue(proDetails.contains("profilePic=premium-avatar.png"));
    }

    @Test
    @DisplayName("Omitting core required constructor criteria constraints must throw an IllegalStateException")
    void testBuilderValidationConstraints() {
        // Enforce parameter boundaries inside build execution layer
        assertThrows(IllegalStateException.class, () -> {
            new UserAccount.Builder(null, "test@mail.com").build();
        }, "Should deny account setup if username reference string is missing");

        assertThrows(IllegalStateException.class, () -> {
            new UserAccount.Builder("user123", null).build();
        }, "Should deny account setup if email data parameter is missing");
    }
}
