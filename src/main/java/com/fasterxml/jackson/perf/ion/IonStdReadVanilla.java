package com.fasterxml.jackson.perf.ion;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class IonStdReadVanilla
// 29-Oct-2017, tatu: Let's limit to basic set; technically easy to extend by uncommenting:
//    extends ReadPerfBaseFullJackson<MediaItem>
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new IonFactory());

    private final static InputConverter CBORS = InputConverter.stdConverter(MAPPER);

    public IonStdReadVanilla() {
        super(MediaItem.class, CBORS, MAPPER);
    }
}
