package com.fasterxml.jackson.perf.dzone;

import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteGSON extends DZoneTestBase
{
    protected final Gson gson;
    
    public DZoneWriteGSON()
    {
        gson = new GsonBuilder().create();
    }
    
    @Override
    public int _writeItems(List<MeasurementRecord> items, Writer out)
        throws Exception
    {
        gson.toJson(items, out);
        return items.size();
    }
}
