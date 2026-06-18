package org.lld.behavioral.iterator.aggregator;

// LinkedIn stores its posts in a rigid array structure.
public class LinkedInChannel implements SocialMediaChannel {
    private final Post[] posts;
    private int totalPosts = 0;

    public LinkedInChannel(int maxCapacity) {
        this.posts = new Post[maxCapacity];
    }

    public void addPost(String text, String user) {
        if (totalPosts < posts.length) {
            posts[totalPosts++] = new Post(text, user);
        }
    }

    @Override
    public FeedIterator createIterator() {
        // Pass the private array structure into its dedicated custom traversal engine
        return new LinkedInFeedIterator(this.posts, this.totalPosts);
    }
}

// inner helper class TODO we can could have defined inside the class
// Concrete Iterator specialized for traversing standard fixed arrays
class LinkedInFeedIterator implements FeedIterator {
    private final Post[] posts;
    private final int totalPosts;
    private int position = 0;

    public LinkedInFeedIterator(Post[] posts, int totalPosts) {
        this.posts = posts;
        this.totalPosts = totalPosts;
    }

    @Override
    public boolean hasNext() {
        return position < totalPosts && posts[position] != null;
    }

    @Override
    public Post next() {
        return posts[position++];
    }
}