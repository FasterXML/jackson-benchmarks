package com.fasterxml.jackson.perf.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;

import com.fasterxml.jackson.core.*;

public class InputConverter
{
    protected final EnumMap<InputData, byte[]> _data;

    public InputConverter(JsonFactory targetFactory)
    {
        EnumMap<InputData, byte[]> data = new EnumMap<>(InputData.class);

        // special case, mostly just to support non-converted JSON
        if (targetFactory == null) {
            for (InputData input : InputData.values()) {
                data.put(input, input.bytes());
            }
        } else {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(4000);
            final JsonFactory jsonF = new JsonFactory();
    
            for (InputData input : InputData.values()) {
                try {
                    JsonParser in = jsonF.createParser(input.bytes());
                    bytes.reset();
                    JsonGenerator out = targetFactory.createGenerator(bytes);
                    while (in.nextToken() != null) {
                        out.copyCurrentStructure(in);
                    }
                    in.close();
                    out.close();
                    data.put(input, bytes.toByteArray());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        _data = data;
    }

    public byte[] bytes(InputData type) {
        return _data.get(type);
    }
}
