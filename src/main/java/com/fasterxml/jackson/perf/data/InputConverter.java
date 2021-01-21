package com.fasterxml.jackson.perf.data;

import java.io.ByteArrayOutputStream;
import java.util.EnumMap;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItems;

public class InputConverter
    extends MinimalInputConverter
{
    protected final EnumMap<InputData, byte[]> _data;

    protected InputConverter(EnumMap<InputData, byte[]> genericData,
    		byte[] mib)
    {
        super(mib);
        _data = genericData;
    }

    public static InputConverter nopConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData, byte[]> data = new EnumMap<InputData, byte[]>(InputData.class);

        // special case, mostly just to support non-converted JSON
        for (InputData input : InputData.values()) {
        	data.put(input, input.bytes());
        }
        // but can't avoid serializing typed values
        byte[] mib = targetMapper.writeValueAsBytes(MediaItems.stdMediaItem());
        return new InputConverter(data, mib);
    }

    public static InputConverter stdConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData, byte[]> data = new EnumMap<InputData, byte[]>(InputData.class);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(4000);
        final JsonFactory jsonF = new JsonFactory();
        final TokenStreamFactory targetF = targetMapper.tokenStreamFactory();

        for (InputData input : InputData.values()) {
            JsonParser in = jsonF.createParser(ObjectReadContext.empty(),
                    input.bytes());
            bytes.reset();
            JsonGenerator out = targetF.createGenerator(ObjectWriteContext.empty(),
                    bytes);
            while (in.nextToken() != null) {
                out.copyCurrentStructure(in);
            }
            in.close();
            out.close();
            data.put(input, bytes.toByteArray());
        }
        byte[] mib = targetMapper.writeValueAsBytes(MediaItems.stdMediaItem());
        return new InputConverter(data, mib);
    }

    public byte[] bytes(InputData type) {
        return _data.get(type);
    }
}
