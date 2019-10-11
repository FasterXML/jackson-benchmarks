package com.fasterxml.jackson.perf;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class WritePerfBaseFullJackson<T>
    extends WritePerfBasicJackson<T>
    implements WritePerfTestFull
{
    protected final ObjectMapper MAPPER;

    protected final ObjectWriter UNTYPED_WRITER;

    protected final ObjectWriter NODE_WRITER;

    // Note on these two variables: looks like there is some (de?)optimization
    // that changes results if we use conversion operations too early.
    // To avoid that, we will lazily do conversions

    protected final AtomicReference<JsonNode> nodeRef = new AtomicReference<JsonNode>();

    protected final AtomicReference<Object> untypedRef = new AtomicReference<Object>();

    protected WritePerfBaseFullJackson(ObjectMapper mapper) {
        super(mapper);
        MAPPER = mapper;
        UNTYPED_WRITER = mapper.writerFor(Object.class);
        NODE_WRITER = mapper.writerFor(JsonNode.class);
    }

    /*
    /**********************************************************************
    /* Untyped ("map") writing tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writeUntypedMediaItem(Blackhole bh) throws Exception {
        Object value = untypedRef.get();
        if (value == null) {
            value = MAPPER.convertValue(item, Map.class);
            untypedRef.set(value);
        }
        bh.consume(write(value, UNTYPED_WRITER));
    }

    /*
    /**********************************************************************
    /* Tree ("node") writing tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writeNodeMediaItem(Blackhole bh) throws Exception {
        JsonNode node = nodeRef.get();
        if (node == null) {
            node = MAPPER.valueToTree(item);
            nodeRef.set(node);
        }
        bh.consume(write(node, NODE_WRITER));
    }
}
