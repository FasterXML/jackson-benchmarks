package com.fasterxml.jackson.perf;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class WritePerfBaseFullJackson<T>
    extends WritePerfBasicJackson<T>
    implements WritePerfTestFull
{
    protected final ObjectWriter UNTYPED_WRITER;

    protected final ObjectWriter NODE_WRITER;

    protected final JsonNode node;

    protected final Object untyped;

    protected WritePerfBaseFullJackson(ObjectMapper mapper) {
        super(mapper);
        UNTYPED_WRITER = mapper.writerFor(Object.class);
        NODE_WRITER = mapper.writerFor(JsonNode.class);
        node = mapper.valueToTree(item);
        untyped = mapper.convertValue(item, Map.class);
    }

    /*
    /**********************************************************************
    /* Untyped ("map") writing tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writeUntypedMediaItem(BlackHole bh) throws Exception {
        bh.consume(write(untyped, UNTYPED_WRITER));
    }

    /*
    /**********************************************************************
    /* Tree ("node") writing tests
    /**********************************************************************
     */
    
    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writeNodeMediaItem(BlackHole bh) throws Exception {
        bh.consume(write(node, NODE_WRITER));
    }
}
