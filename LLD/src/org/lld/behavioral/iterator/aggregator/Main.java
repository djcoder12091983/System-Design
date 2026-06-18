package org.lld.behavioral.iterator.aggregator;

// dummy test class
// TODO: Add actual test cases

// The client application UI loops over entirely different underlying collections
// without ever knowing whether it is handling an array or a list.

public class Main {

    // The UI printing engine is completely decoupled from structural implementation choices
    private static void printFeed(FeedIterator iterator) {
        while (iterator.hasNext()) {
            Post post = iterator.next();
            System.out.println(" -> " + post);
        }
    }

    public static void main(String[] args) {
        // 1. Setup LinkedIn Data Source (Array Base)
        LinkedInChannel linkedIn = new LinkedInChannel(5);
        linkedIn.addPost("Thrilled to announce my new role!", "Alice");
        linkedIn.addPost("Looking for senior backend developers. DM me!", "Bob");

        // 2. Setup Twitter Data Source (List Base)
        TwitterChannel twitter = new TwitterChannel();
        twitter.addTweet("Breaking news: Java 27 is now out!", "TechNews");
        twitter.addTweet("Coffee first, code later.", "DevMeme");

        // 3. Extract the iterators over the common abstraction contract
        FeedIterator linkedInIterator = linkedIn.createIterator();
        FeedIterator twitterIterator = twitter.createIterator();

        // 4. Uniform rendering execution loop
        System.out.println("=== RENDERING LINKEDIN POSTS ===");
        printFeed(linkedInIterator);

        System.out.println("\n=== RENDERING TWITTER STREAM ===");
        printFeed(twitterIterator);
    }
}