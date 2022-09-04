package com.fasterxml.jackson.perf.misc;

import java.io.OutputStream;
import java.io.Writer;

import tools.jackson.jr.ob.JSON;
import tools.jackson.jr.ob.JSONComposer;
import tools.jackson.jr.ob.comp.ArrayComposer;

public class JsonEncodeUsingJackson extends JsonEncodeBase
{
    private final static JSON json = JSON.std;

    @Override
    protected void encode(StringBuilder sb, String[] values) throws Exception {
        JSONComposer<String> comp = json.composeString();
        ArrayComposer<JSONComposer<String>> arrayComp = comp.startArray();
        for (String value : values) {
            arrayComp.add(value);
        }
        sb.append(arrayComp.end().finish());
    }

    @Override
    protected void encode(Writer w, String[] values) throws Exception {
        JSONComposer<OutputStream> comp = json.composeTo(w);
        ArrayComposer<JSONComposer<OutputStream>> arrayComp = comp.startArray();
        for (String value : values) {
            arrayComp.add(value);
        }
        arrayComp.end().finish();
    }

    @Override
    protected void encode(StringBuilder sb, String value) throws Exception {
        sb.append(
            json.composeString().startArray()
                .add(value)
            .end()
            .finish()
        );
    }

    @Override
    protected void encode(Writer w, String value) throws Exception {
        json.composeTo(w).startArray()
            .add(value)
            .end()
        .finish();
    }
}
