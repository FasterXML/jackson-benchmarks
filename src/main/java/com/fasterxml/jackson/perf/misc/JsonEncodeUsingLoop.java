package com.fasterxml.jackson.perf.misc;

import java.io.IOException;
import java.io.Writer;

public class JsonEncodeUsingLoop extends JsonEncodeBase
{
    @Override
    protected void encode(StringBuilder builder, String[] values) throws Exception {
        builder.append('[');
        _encode(builder, values[0]);
        for (int i = 0, len = values.length; i < len; ++i) {
            _encode(builder, values[i]);
        }
        builder.append(']');
    }

    @Override
    protected void encode(Writer w, String[] values) throws Exception {
        w.write('[');
        _encode(w, values[0]);
        for (int i = 0, len = values.length; i < len; ++i) {
            _encode(w, values[i]);
        }
        w.write(']');
    }
 
    @Override
    protected void encode(StringBuilder builder, String value) throws Exception {
        builder.append('[');
        _encode(builder, value);
        builder.append(']');
    }

    @Override
    protected void encode(Writer w, String value) throws Exception {
        w.write('[');
        _encode(w, value);
        w.write(']');
    }

    private final void _encode(StringBuilder builder, String value) {
        for (int i = 0, len = value.length(); i < len; ++i) {
            char c = value.charAt(i);
            switch (c) {
            case '"':
                builder.append("\\\"");
                break;
            case '\\':
                builder.append("\\\\");
                break;
            case '/':
                builder.append("\\/");
                break;
            case '\b':
                builder.append("\\b");
                break;
            case '\f':
                builder.append("\\f");
                break;
            case '\n':
                builder.append("\\n");
                break;
            case '\r':
                builder.append("\\r");
                break;
            case '\t':
                builder.append("\\t");
                break;
            default:
                builder.append(c);
                break;
            }
        }
    }

    private final void _encode(Writer w, String value) throws IOException {
        for (int i = 0, len = value.length(); i < len; ++i) {
            char c = value.charAt(i);
            switch (c) {
            case '"':
                w.write("\\\"");
                break;
            case '\\':
                w.write("\\\\");
                break;
            case '/':
                w.write("\\/");
                break;
            case '\b':
                w.write("\\b");
                break;
            case '\f':
                w.write("\\f");
                break;
            case '\n':
                w.write("\\n");
                break;
            case '\r':
                w.write("\\r");
                break;
            case '\t':
                w.write("\\t");
                break;
            default:
                w.write(c);
                break;
            }
        }
    }
}
