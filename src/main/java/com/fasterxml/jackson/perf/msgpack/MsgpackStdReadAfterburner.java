package com.fasterxml.jackson.perf.msgpack;

import java.util.concurrent.TimeUnit;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class MsgpackStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    // 11-Apr-2018, tatu: won't work until factory converted to 3.x
    /*
    private static final ObjectMapper MAPPER = ObjectMapper.builder(new MessagePackFactory())
            .addModule(new AfterburnerModule())
            .build();
            */
    private static final ObjectMapper MAPPER = new ObjectMapper(new MessagePackFactory());

    public MsgpackStdReadAfterburner() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}
