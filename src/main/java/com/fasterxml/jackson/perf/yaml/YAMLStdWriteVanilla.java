package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class YAMLStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    public YAMLStdWriteVanilla() {
        super(new YAMLMapper());
    }
}
