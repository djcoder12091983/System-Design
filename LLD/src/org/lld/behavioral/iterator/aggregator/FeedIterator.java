package org.lld.behavioral.iterator.aggregator;

// iterates the feed from social media

//While Java has a built-in java.util.Iterator,
// building a custom one helps illustrate exactly how the mechanics function behind the scenes.

// TODO we can think of coming up with context/domain based next and hasNext methods

public interface FeedIterator {
    boolean hasNext();
    Post next();
}