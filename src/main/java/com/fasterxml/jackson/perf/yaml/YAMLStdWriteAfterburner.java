package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class YAMLStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    public YAMLStdWriteAfterburner() {
        super(new YAMLMapper().registerModule(new AfterburnerModule()));
    }
}
