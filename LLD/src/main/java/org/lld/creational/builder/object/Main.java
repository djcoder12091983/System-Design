package org.lld.creational.builder.object;

public class Main {
    // TODO we have created dummy test case but in reality we need to setup proper test cases
    public static void main(String[] args) {
        // Avoids telescoping and stays clear: No long lists of ambiguous booleans/strings
        UserAccount standardUser = new UserAccount.Builder("johndoe", "john@example.com")
                .darkMode(false)
                .build();

        UserAccount premiumUser = new UserAccount.Builder("alex_pro", "alex@biz.com")
                .isPremium(true)
                .profilePicUrl("avatar5.jpg")
                .twoFactorEnabled(true)
                .build();

        System.out.println(standardUser);
        System.out.println(premiumUser);
    }
}
