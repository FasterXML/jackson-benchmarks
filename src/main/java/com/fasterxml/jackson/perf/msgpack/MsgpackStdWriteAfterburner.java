package com.fasterxml.jackson.perf.msgpack;

import org.msgpack.jackson.dataformat.msgpack.MessagePackFactory;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;

@State(Scope.Group) // Thread, Group or Benchmark
public class MsgpackStdWriteAfterburner
    extends WritePerfBasicJackson
{
    private static final ObjectMapper MAPPER;
    static {
    	MessagePackFactory f = new MessagePackFactory();
    	// configure differently?
    	MAPPER = new ObjectMapper(f);
    }

    public MsgpackStdWriteAfterburner() {
        super(MAPPER);
    }
}
