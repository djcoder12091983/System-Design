package org.lld.behavioral.iterator.aggregator;

import java.util.ArrayList;
import java.util.List;

// Twitter/X stores its posts inside a standard dynamic Java ArrayList.
public class TwitterChannel implements SocialMediaChannel {
    private final List<Post> tweets = new ArrayList<>();

    public void addTweet(String text, String user) {
        tweets.add(new Post(text, user));
    }

    @Override
    public FeedIterator createIterator() {
        return new TwitterFeedIterator(this.tweets);
    }
}

// inner helper class TODO we can could have defined inside the class
// Concrete Iterator specialized for traversing standard collections
class TwitterFeedIterator implements FeedIterator {
    private final List<Post> tweets;
    private int position = 0;

    public TwitterFeedIterator(List<Post> tweets) {
        this.tweets = tweets;
    }

    @Override
    public boolean hasNext() {
        return position < tweets.size();
    }

    @Override
    public Post next() {
        return tweets.get(position++);
    }
}