package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdWriteVanilla
    extends WritePerfBaseFullJackson<MediaItem>
{
    public SmileStdWriteVanilla() {
        super(PerfDefaultsForSmile.smileMapper());
    }
}
