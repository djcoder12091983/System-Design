package org.lld.structural.decorator.stream;

// this is implementation of root class and we will wrap other decorators on top of it
public class RawDataStream implements DataStream {
    @Override
    public void write(String data) {
        // Simulating the final delivery write block
        System.out.println("[Disk Storage] Writing Final Raw Bytes: " + data);
    }
}
