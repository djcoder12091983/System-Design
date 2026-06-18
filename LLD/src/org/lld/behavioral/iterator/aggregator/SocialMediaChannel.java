package org.lld.behavioral.iterator.aggregator;

// This defines the contract for any social media platform collection to supply
// its respective iterator wrapper.
public interface SocialMediaChannel {
    FeedIterator createIterator();
}