package org.lld.structural.decorator.stream;

// this is root class for writing data to stream
// now we will have one implementation of it on top it we will wrap other decorators
public interface DataStream {
    void write(String data);
}
