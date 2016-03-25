package com.fasterxml.jackson.perf.javaprops;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropertiesStdReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final JavaPropsMapper MAPPER = new JavaPropsMapper();

    private final static InputConverter PROPS = InputConverter.stdConverter(MAPPER);

    public PropertiesStdReadVanilla() {
        super(MediaItem.class, PROPS, MAPPER);
    }
}
