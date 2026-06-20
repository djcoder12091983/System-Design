package org.lld.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.iterator.aggregator.FeedIterator;
import org.lld.behavioral.iterator.aggregator.LinkedInChannel;
import org.lld.behavioral.iterator.aggregator.Post;
import org.lld.behavioral.iterator.aggregator.TwitterChannel;

import static org.junit.jupiter.api.Assertions.*;

// These tests verify that distinct structural collections (Array-based vs. List-based) are traversed using
// a completely unified interface, and that the index state counters are safely isolated per instance.
@DisplayName("Iterator Pattern - Unified Data Feed Aggregator Test Suite")
class AggregationIteratorTest extends DPTestSuit {

    @Test
    @DisplayName("LinkedInFeedIterator must transparently traverse a fixed-size structural Array")
    void testArrayBasedIteratorTraversal() {
        LinkedInChannel linkedIn = new LinkedInChannel(3);
        linkedIn.addPost("Hello LinkedIn", "Alice");
        linkedIn.addPost("Hiring developers", "Bob");

        FeedIterator iterator = linkedIn.createIterator();

        assertTrue(iterator.hasNext(), "Iterator should discover initial data element");
        Post post1 = iterator.next();
        assertEquals("Alice: \"Hello LinkedIn\"", post1.toString());

        assertTrue(iterator.hasNext());
        Post post2 = iterator.next();
        assertEquals("Bob: \"Hiring developers\"", post2.toString());

        assertFalse(iterator.hasNext(), "Iterator must exhaust exactly matching array entries");
    }

    @Test
    @DisplayName("TwitterFeedIterator must transparently traverse a dynamic ArrayList")
    void testListBasedIteratorTraversal() {
        TwitterChannel twitter = new TwitterChannel();
        twitter.addTweet("Java 27 is out!", "TechNews");

        FeedIterator iterator = twitter.createIterator();

        assertTrue(iterator.hasNext());
        assertEquals("TechNews: \"Java 27 is out!\"", iterator.next().toString());
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Multiple iterators on the same collection must operate with isolated counter states")
    void testMultipleIndependentIterators() {
        TwitterChannel twitter = new TwitterChannel();
        twitter.addTweet("Tweet 1", "User1");
        twitter.addTweet("Tweet 2", "User2");

        FeedIterator it1 = twitter.createIterator();
        FeedIterator it2 = twitter.createIterator();

        // Advance Iterator 1 completely
        it1.next();
        it1.next();
        assertFalse(it1.hasNext());

        // Iterator 2 must remain completely unaffected at index zero
        assertTrue(it2.hasNext(), "Iterator 2 state counter polluted by Iterator 1 execution");
        assertEquals("User1: \"Tweet 1\"", it2.next().toString());
    }
}