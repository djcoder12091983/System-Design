package org.lld.structural.decorator.stream;

// abstract decorator which is at the root of complex pipelines
public abstract class DataStreamDecorator implements DataStream {
    // Composition: Holding the component object layer
    protected final DataStream wrappedStream;

    protected DataStreamDecorator(DataStream stream) {
        this.wrappedStream = stream;
    }

    @Override
    public void write(String data) {
        // Default behavior: Delegate directly over to the underlying stream
        wrappedStream.write(data);
    }
}
