package com.fasterxml.jackson.perf.msgpack;

import org.msgpack.jackson.dataformat.msgpack.MessagePackFactory;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class MsgpackStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
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
