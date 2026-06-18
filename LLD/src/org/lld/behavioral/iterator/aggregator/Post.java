package org.lld.behavioral.iterator.aggregator;

// social media post
public class Post {
    private final String content;
    private final String author;

    public Post(String content, String author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return author + ": \"" + content + "\"";
    }
}