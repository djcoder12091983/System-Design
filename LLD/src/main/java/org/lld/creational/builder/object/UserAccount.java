package org.lld.creational.builder.object;

// building object using builder pattern avoids telescoping constructors
public class UserAccount {
    // Required fields
    private final String username;
    private final String email;

    // Optional fields (with defaults)
    private final boolean isPremium;
    private final String profilePicUrl;
    private final boolean twoFactorEnabled;
    private final boolean darkMode;

    // Private constructor ensures the object can ONLY be built via the Builder
    private UserAccount(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.isPremium = builder.isPremium;
        this.profilePicUrl = builder.profilePicUrl;
        this.twoFactorEnabled = builder.twoFactorEnabled;
        this.darkMode = builder.darkMode;
    }

    // Static nested Builder class
    public static class Builder {
        private final String username; // Required
        private final String email;    // Required
        private boolean isPremium = false; // Optional default
        private String profilePicUrl = "default.png";
        private boolean twoFactorEnabled = false;
        private boolean darkMode = true;

        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public Builder isPremium(boolean isPremium) {
            this.isPremium = isPremium;
            return this; // Returns 'this' to allow method chaining
        }

        public Builder profilePicUrl(String url) {
            this.profilePicUrl = url;
            return this;
        }

        public Builder twoFactorEnabled(boolean enabled) {
            this.twoFactorEnabled = enabled;
            return this;
        }

        public Builder darkMode(boolean darkMode) {
            this.darkMode = darkMode;
            return this;
        }

        // The final build method that executes validation
        public UserAccount build() {
            if (username == null || email == null) {
                throw new IllegalStateException("Missing required parameters");
            }
            return new UserAccount(this);
        }
    }

    @Override
    public String toString() {
        return "UserAccount{" + "username='" + username + '\'' + ", email='" + email + '\'' +
                ", isPremium=" + isPremium + ", profilePic=" + profilePicUrl + '}';
    }
}