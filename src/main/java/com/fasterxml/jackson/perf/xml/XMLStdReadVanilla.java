package com.fasterxml.jackson.perf.xml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class XMLStdReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = StaxProvider.xmlMapper();

    private final static MinimalInputConverter XML = MinimalInputConverter.minimalConverter(MAPPER);

    public XMLStdReadVanilla() {
        super(MediaItem.class, XML, MAPPER);
    }
}
