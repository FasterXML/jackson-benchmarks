package com.fasterxml.jackson.perf.xml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class XMLStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    public XMLStdWriteAfterburner() {
        super(StaxProvider.xmlMapper(true));
    }
}
