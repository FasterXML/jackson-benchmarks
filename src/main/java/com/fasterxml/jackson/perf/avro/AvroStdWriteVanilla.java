package com.fasterxml.jackson.perf.avro;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class AvroStdWriteVanilla
    extends WritePerfBasicJackson
{
    private final static AvroFactory _f = new AvroFactory();
    private static final ObjectMapper MAPPER = new ObjectMapper(_f);

    private final static AvroSchema _mediaItemSchema;
    static {
	    AvroSchemaGenerator gen = new AvroSchemaGenerator();
	    try {
	    	MAPPER.acceptJsonFormatVisitor(MediaItem.class, gen);
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
	    _mediaItemSchema = gen.getGeneratedSchema();
    }

    public AvroStdWriteVanilla() {
        super(MAPPER, _mediaItemSchema);
    }
}
